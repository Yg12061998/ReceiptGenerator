package com.yogigupta1206.invoicereceiptmaker.domain.use_case.quotation

import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.domain.repository.QuotationRepository

class SaveQuotation(
    private val quotationRepository: QuotationRepository
) {

    @Throws(IllegalArgumentException::class)
    suspend operator fun invoke(
        customer: Customer?,
        quotation: Quotation,
        itemList: List<QuotationItem>
    ) {
        if (customer?.id == null)
            throw IllegalArgumentException("Please add customer")

        if (itemList.isEmpty())
            throw IllegalArgumentException("Please add product")

        val updatedQuotation = quotation.copy(
            customerId = customer.id
        )

        quotationRepository.saveQuotation(updatedQuotation, itemList)
    }
}