
package com.fordmps.mobileapp.move

import com.ford.dashboard.models.VehicleInfo
import com.ford.dashboard.models.VehicleInfoImpl
import com.ford.vehiclecommon.models.Vehicle
import java.util.*

//
//import com.ford.repo.capabilities.VehicleCapabilitiesRepository
//import com.ford.xapi.models.response.VehicleCapability
//import com.ford.xapi.models.response.XapiAuthStatus
//import com.fordmps.mobileapp.shared.configuration.ConfigurationProvider
import io.reactivex.Observable
//import io.reactivex.functions.BiFunction
//import javax.inject.Inject

interface VehicleAuthorizationDataManager {
    fun setVehicleData(updatedVehicleInfo: VehicleInfo, vehicleCapability: VehicleCapability) {

    }
    fun getAuthorizationData(vehicleInfo: VehicleInfo): Observable<Pair<VehicleInfo, VehicleCapability?>>{
        return Observable.just(Pair(VehicleInfoImpl(),null))
    }

    fun isPendingReset() : Boolean
    fun xApiAuthorizationStatus() : Int
//    val authorizationData: VehicleInfo

    //    val authorizationData: VehicleInfo
    val vin: String
    val XApiAuthorizationStatus: Vehicle.Authorization
    val pendingReset: Boolean
}

//@OpenForTesting
//class VehicleAuthorizationDataManager @Inject constructor(
//    private val configurationProvider: ConfigurationProvider,
//    private val vehicleInfoProvider: VehicleInfoProvider,
//    private val vehicleCapabilitiesRepository: VehicleCapabilitiesRepository
//) : VehicleAuthorizationStatus, VehicleAuthorizationDataManagerInterface {
//
//    private lateinit var vehicleAuthorizationStatus :VehicleAuthorizationStatus
//
//    override fun getVin(): String = vehicleAuthorizationStatus.getVin()
//
//    override fun getXApiAuthorizationStatus(): XapiAuthStatus? = vehicleAuthorizationStatus.getXApiAuthorizationStatus()
//
//    override fun isPendingReset(): Boolean = vehicleAuthorizationStatus.isPendingReset()
//
//    fun getAuthorizationData(vehicleInfo: VehicleInfo): Observable<Pair<VehicleInfo, VehicleCapability?>> =
//        if (configurationProvider.configuration.isDashboardXApiPhase2Enabled)
//            getAuthorizationDataObservable(vehicleInfo)
//        else
//            vehicleInfoProvider.getVehicleAuthStatus(vehicleInfo).map { Pair<VehicleInfo, VehicleCapability?>(it, null) }
//
//    fun setVehicleData(vehicleInfo: VehicleInfo, vehicleCapability: VehicleCapability?) {
//        if (configurationProvider.configuration.isDashboardXApiPhase2Enabled) {
//            vehicleCapability?.let { vehicleAuthorizationStatus = VehicleCapabilityAuthorizationStatusImpl(it) }
//        } else {
//            vehicleAuthorizationStatus = VehicleInfoAuthorizationStatusImpl(vehicleInfo)
//        }
//    }
//
//    private fun getAuthorizationDataObservable(vehicleInfo: VehicleInfo): Observable<Pair<VehicleInfo, VehicleCapability?>> {
//        return Observable.combineLatest(vehicleInfoProvider.getVehicleAuthStatus(vehicleInfo),
//                vehicleCapabilitiesRepository.getVehicleCapability(vehicleInfo.vin),
//            BiFunction { updatedVehicleInfo: VehicleInfo, vehicleCapability: VehicleCapability ->
//                Pair<VehicleInfo, VehicleCapability?>(updatedVehicleInfo, vehicleCapability)
//            }
//        )
//    }
//}
