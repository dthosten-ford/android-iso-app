package com.ford.vehiclecommon.models

import com.ford.vehiclecommon.enums.Source
//import com.ford.vehiclecommon.utils.OpenForTesting
//import javax.inject.Inject

//TODO: This will be removed, do not rely on it. Temporary bridge to enable rapid integration

//@OpenForTesting
//class IntegrationUtil @Inject constructor() {
class IntegrationUtil constructor() {
    @JvmOverloads
    fun mutate(profile: GarageVehicleProfile, source: Source, isTcuEnabled: Boolean = profile.getTcuEnabled()): GarageVehicleProfile =
            object : GarageVehicleProfile {
                override fun getVin() = profile.getVin()
                override fun getMake() = profile.getMake()
                override fun getModel() = profile.getModel()
                override fun getYear() = profile.getYear()
                override fun getVehicleImage() = profile.getVehicleImage()
                override fun getTcuEnabled() = isTcuEnabled
                override fun getNickName() = profile.getNickName()
                override fun getLocalizedModelName() = profile.getLocalizedModelName()
                override fun getJointVenture() = profile.getJointVenture()
                override fun getSDNSourceForTCU() = source
            }
}



fun blankAuthStatus() = object : VehicleAuthStatus {
    override fun isSecondaryAuthPending() = false
    override fun isAuthorizationPending() = false
    override fun isPreAuthorized() = false
    override fun hasAuthorizationStatus() = false
    override fun isPendingReset() = false
    override fun isAuthorized() = false
}

fun getAuthProfile(vehicleAuthStatusProfile: VehicleAuthStatusProfile): VehicleAuthProfile =
        VehicleAuthProfile(
                vehicleAuthStatusProfile.garageVehicleProfile,
                vehicleAuthStatusProfile.vehicleAuthStatus
                        .takeIf { it.isPresent }
                        ?.get()
                        ?: blankAuthStatus()
        )
