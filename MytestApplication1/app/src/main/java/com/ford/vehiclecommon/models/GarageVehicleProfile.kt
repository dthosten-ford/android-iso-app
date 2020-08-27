package com.ford.vehiclecommon.models

import com.ford.vehiclecommon.enums.Source
//import com.ford.vehiclecommon.enums.VehicleProductType
import com.google.common.base.Optional
import io.reactivex.Single

interface GarageVehicleProfile {
    fun getVin(): String
    fun getMake(): String
    fun getModel(): String
    fun getYear(): String
    fun getVehicleImage(): String
    fun getTcuEnabled(): Boolean
    fun getNickName(): Optional<String>
    fun getLocalizedModelName(): Optional<String>
    fun getJointVenture(): String
    fun getSDNSourceForTCU(): Source
}

