package com.importre.kotlin.cycle.example.rest.util

object ImageUtils {

    fun getGoogleMapUrl(width: Int, height: Int,
                        lat: Float, lng: Float,
                        zoom: Int): String {
        return "http://maps.google.com/maps/api/staticmap?" +
                "center=$lat,$lng&" +
                "zoom=$zoom&size=${width}x$height" +
                "&sensor=false"
    }
}
