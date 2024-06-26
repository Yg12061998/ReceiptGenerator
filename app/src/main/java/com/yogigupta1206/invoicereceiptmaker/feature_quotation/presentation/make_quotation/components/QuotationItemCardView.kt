package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.DiscountType
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotationItemCardView(
    modifier: Modifier = Modifier,
    item: QuotationItemWithProduct,
    onDeleteClicked: () -> Unit,
    onItemClicked: () -> Unit,
) {

    Box(modifier = modifier) {
        ElevatedCard(
            onClick = onItemClicked,
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .padding(2.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(13.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            modifier = Modifier
                                .padding(end = 16.dp),
                            text = item.product.name ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            modifier = Modifier.clickable(onClick = onDeleteClicked),
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete product",
                            tint = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            modifier = Modifier
                                .padding(end = 16.dp),
                            text = "Amount",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Light
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            text = "${item.quotationItem.quantity} * ${item.quotationItem.price} = ${item.quotationItem.quantity * item.quotationItem.price}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Light,
                            softWrap = true
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            modifier = Modifier
                                .padding(end = 16.dp),
                            text = "Discount ${if (item.quotationItem.discountType == DiscountType.PERCENTAGE) "(${item.quotationItem.discount}%)" else ""}:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Light
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            text = "-${if (item.quotationItem.discountType == DiscountType.PERCENTAGE) (item.quotationItem.quantity * item.quotationItem.price) * (item.quotationItem.discount / 100) else item.quotationItem.discount}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Light
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            modifier = Modifier
                                .padding(end = 16.dp),
                            text = "GST (${item.quotationItem.gst}%)",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Light
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            text = item.quotationItem.totalGst.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Light
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            modifier = Modifier
                                .padding(end = 16.dp),
                            text = "Total amount",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Light
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            text = item.quotationItem.totalAmount.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
        }
    }
}