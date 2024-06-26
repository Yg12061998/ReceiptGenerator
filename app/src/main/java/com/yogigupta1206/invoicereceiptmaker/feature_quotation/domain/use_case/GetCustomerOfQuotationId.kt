package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository

class GetCustomerOfQuotationId(
    private val quotationRepository: QuotationRepository
) {
    operator fun invoke(quotationId: Long) =
        quotationRepository.getCustomerOfQuotationId(quotationId)

}