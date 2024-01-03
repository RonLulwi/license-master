package com.ronlu.licensemaster.data

import com.google.gson.annotations.SerializedName

data class CarResponse<T>(
    @SerializedName("result")
    val result: Result<T>
)
data class Result<T>(
    @SerializedName("records")
    val records: List<T>
)

sealed class VehicleDto {

    data class CarD(
        @SerializedName("mispar_rechev")
        val plateNumber: Int,

        @SerializedName("tzeva_rechev")
        val color: String? = null,

        @SerializedName("shnat_yitzur")
        val year: Int? = null,

        @SerializedName("kinuy_mishari")
        val title: String? = null,

        @SerializedName("baalut")
        val isPrivate: String? = null,

        @SerializedName("tozeret_nm")
        val manufacture: String? = null,

        @SerializedName("ramat_gimur")
        val finishLevel: String? = null,

        @SerializedName("sug_delek_nm")
        val fuelType: String? = null
    ) : VehicleDto()

    data class MotorcycleD(
        @SerializedName("mispar_rechev")
        val plateNumber: Int,

        @SerializedName("tzeva_rechev")
        val color: String? = null,

        @SerializedName("shnat_yitzur")
        val year: Int? = null,

        @SerializedName("kinuy_mishari")
        val title: String? = null,

        @SerializedName("baalut")
        val isPrivate: String? = null,

        @SerializedName("tozeret_nm")
        val manufacture: String? = null,

        @SerializedName("ramat_gimur")
        val finishLevel: String? = null,

        @SerializedName("sug_delek_nm")
        val fuelType: String? = null,

        @SerializedName("zmig_kidmi")
        val frontTire: String? = null,

        @SerializedName("zmig_ahori")
        val rearTire: String? = null

    ) : VehicleDto()

    data class PublicD(
        @SerializedName("mispar_rechev")
        val plateNumber: Int,

        @SerializedName("tzeva_rechev")
        val color: String? = null,

        @SerializedName("shnat_yitzur")
        val year: Int? = null,

        @SerializedName("sug_rechev_EU_cd")
        val euLevel: String? = null,

        @SerializedName("sug_rechev_nm")
        val vehicleType: String? = null,

        @SerializedName("tozeret_nm")
        val manufacture: String? = null,

        @SerializedName("mispar_mekomot")
        val numberOfSeats: String? = null,

        @SerializedName("mishkal_kolel")
        val weight: Int? = null
    ) :VehicleDto()
}