package com.ford.vehiclecommon.models
//
//import com.ford.vehiclecommon.utils.OpenForTesting
import com.google.common.base.Optional
//
////@OpenForTesting
data class VehicleAuthStatusProfile (
        val garageVehicleProfile: GarageVehicleProfile,
        val vehicleStatus: Optional<VehicleStatus>,
        val vehicleAuthStatus: Optional<VehicleAuthStatus>
)