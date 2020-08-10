package com.ford.blanco.models

data class Vehicle(
        private val garageLocation: Address,
        private val make: String,
        private val model: String,
        private val vin: String,
        private val year: Int)