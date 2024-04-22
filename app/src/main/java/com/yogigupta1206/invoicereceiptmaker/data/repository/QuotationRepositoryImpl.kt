package com.yogigupta1206.invoicereceiptmaker.data.repository

import com.yogigupta1206.invoicereceiptmaker.data.data_source.db.QuotationDao
import com.yogigupta1206.invoicereceiptmaker.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationWithCustomer
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationWithCustomerAndItems
import com.yogigupta1206.invoicereceiptmaker.domain.repository.QuotationRepository
import kotlinx.coroutines.flow.Flow

class QuotationRepositoryImpl(
    private val quotationDao: QuotationDao
) : QuotationRepository {

    override suspend fun addQuotation(quotation: Quotation, itemList: List<QuotationItem>) {
        val quotationId = quotationDao.insertQuotationAndGetId(quotation)
        val quotationItems = itemList.map { it.copy(quotationId = quotationId) }
        quotationDao.insertQuotationItems(quotationItems)
    }

    override suspend fun deleteQuotationById(quotationId: Long) {
        quotationDao.deleteQuotationById(quotationId)
    }

    override suspend fun updateQuotation(quotation: Quotation, itemList: List<QuotationItem>) {
        quotationDao.updateQuotation(quotation)

        // Delete all items and insert new items
        quotation.id?.let { quotationId ->
            quotationDao.deleteQuotationItemsByQuotationId(quotationId)
            val quotationItems = itemList.map { it.copy(quotationId = quotationId) }
            quotationDao.insertQuotationItems(quotationItems)
        }
    }

    override fun getAllQuotationsWithCustomer(): Flow<List<QuotationWithCustomer>> {
        return quotationDao.getAllQuotationsWithCustomerDetails()
    }

    override suspend fun getQuotationAndQuotationItemsById(quotationId: Long): QuotationWithCustomerAndItems {
        return quotationDao.getQuotationsWithCustomerAndItemsById(quotationId)
    }

}