package com.example.data.di.model

import com.example.data.di.model.response.CartItem
import com.example.domain.di.model.SummaryData
import kotlinx.serialization.Serializable

@Serializable
data class Summary(
    val discount: Double,
    val items: List<CartItem>,
    val shipping: Double,
    val subtotal: Double,
    val tax: Double,
    val total: Double
)
{
    fun toSummaryData() = SummaryData(
        discount = discount,
        items = items.map { it.toCartItemModel() },
        shipping = shipping,
        subtotal = subtotal,
        tax = tax,
        total = total
    )
}