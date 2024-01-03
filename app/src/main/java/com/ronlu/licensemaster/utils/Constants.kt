package com.ronlu.licensemaster.utils

class Constants {
    companion object{
        const val BASE_URL = "https://data.gov.il/api/3/action/"
        const val API_CAR_KEY = "053cea08-09bc-40ec-8f7a-156f0677aff3"
        const val API_MOTORCYCLE_KEY = "bf9df4e2-d90d-4c0a-a400-19e15af8e95f"
        const val API_PUBLIC_KEY = "cf29862d-ca25-4691-84f6-1be60dcb4a1e"
        fun getFilter(plate: String) = "{\"mispar_rechev\": \"$plate\"}";
    }
}