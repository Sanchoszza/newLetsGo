package com.android.letsgo.network.response

import com.google.gson.annotations.SerializedName

class GeocoderResponse {
    @SerializedName("response")
    var response: ResponseData? = null

    fun getResponse(): ResponseData? {
        return response
    }

    class ResponseData {
        @SerializedName("GeoObjectCollection")
        var geoObjectCollection: GeoObjectCollection? = null
    }

    class GeoObjectCollection {
        @SerializedName("featureMember")
        var featureMember: FeatureMember? = null
    }

    class FeatureMember {
        @SerializedName("GeoObject")
        var geoObject: GeoObject? = null
        val formatedAdress: String?
            get() = if (geoObject != null && geoObject?.metaDataProperty != null
                && geoObject!!.metaDataProperty!!.geocoderMetaData != null
                && geoObject!!.metaDataProperty!!.geocoderMetaData!!.address != null) {
                geoObject!!.metaDataProperty!!.geocoderMetaData!!.address!!.formatted
            } else null
    }

    class GeoObject {
        @SerializedName("metaDataProperty")
        var metaDataProperty: MetaDataProperty? = null
    }

    class MetaDataProperty {
        @SerializedName("GeocoderMetaData")
        var geocoderMetaData: GeocoderMetaData? = null
    }

    class GeocoderMetaData {
        @SerializedName("Address")
        var address: Address? = null
    }

    class Address {
        @SerializedName("formatted")
        var formatted: String? = null
    }
}