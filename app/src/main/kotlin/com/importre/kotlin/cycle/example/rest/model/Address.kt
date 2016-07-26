package com.importre.kotlin.cycle.example.rest.model

data class Address(val street: String,
                   val suite: String,
                   val city: String,
                   val zipcode: String,
                   val geo: Geo)
