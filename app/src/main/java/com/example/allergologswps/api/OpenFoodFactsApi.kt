package com.example.allergologswps.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

// Model uproszczony, można rozbudować według potrzeb
 data class ProductResponse(
    val code: String?,
    val product: ProductDetail?
)

data class ProductDetail(
    val product_name: String?,
    val brands: String?,
    val image_url: String?
)

interface OpenFoodFactsApi {
    @GET("api/v2/product/{barcode}.json")
    fun getProduct(
        @Path("barcode") barcode: String,
        @Header("Authorization") auth: String
    ): Call<ProductResponse>
}

