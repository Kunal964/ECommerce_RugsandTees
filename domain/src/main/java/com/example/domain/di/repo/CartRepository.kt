package com.example.domain.di.repo

import com.example.domain.di.model.CartItemModel
import com.example.domain.di.model.CartModel
import com.example.domain.di.model.CartSummary
import com.example.domain.di.model.request.AddCartRequestModel
import com.example.domain.di.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(request: AddCartRequestModel): ResultWrapper<CartModel>

    suspend fun getCart(): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel) : ResultWrapper<CartModel>
    suspend fun deleteItem(cartItemId: Int, userId: Int): ResultWrapper<CartModel>
    suspend fun getCartSummary(userId: Long): ResultWrapper<CartSummary>
}