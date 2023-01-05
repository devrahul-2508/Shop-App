package com.example.shopapp.utility

object Constants {


    object Retrofit{
        const val WITHOUT_HEADERS="without headers"
        const val WITH_HEADERS="with headers"
    }

    const val USER_NAME="USER_NAME"
    const val EMAIL="EMAIL"
    const val IS_ADMIN="IS_ADMIN"
    const val ACCESS_TOKEN = "ACCESS_TOKEN"

    const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"

    const val REST_API_WITH_HEADERS = "RestApiWithHeaders"
    const val REST_API_WITHOUT_HEADERS = "RestApiWithoutHeaders"
    const val BASE_URL="http://192.168.29.89:3000"

    object INTENT_PARAMS{
        const val PRODUCT_ID = "productid"
        const val ORDER_ID = "orderid"
    }
    object VIEW_TYPES{
        const val ON_SALE_VIEWTYPE = 600
        const val TOP_SELLING_VIEWTYPE = 700
        const val TRENDING_VIEWTYPE= 800
    }

}