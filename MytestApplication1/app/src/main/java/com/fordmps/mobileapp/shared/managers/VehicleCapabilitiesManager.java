/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.shared.managers;

import com.fordmps.mobileapp.move.VehicleCapabilitiesResponse;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/*
import com.ford.androidutils.SharedPrefsUtil;
import com.ford.applink.providers.LiveTrafficEligibilityProvider;
import com.ford.applink.providers.VcsAppLinkCapabilityProvider;
import com.ford.rxutils.schedulers.RxSchedulingHelper;
import com.ford.rxutils.schedulers.Threads;
import com.ford.vcs.VcsRepository;
import com.ford.vcs.models.Feature;
import com.ford.vcs.models.VcsAction;
import com.ford.vcs.models.VehicleCapabilitiesResponse;
import com.ford.vcs.vcsutil.VehicleCapabilitiesUtil;
import com.ford.vcs.repositories.RemoteStartCapabilityRepository;
import com.ford.vcs.storage.VehicleSdnTypeProvider;
import com.fordmps.data.enums.SdnType;*/
/*import com.fordmps.mobileapp.shared.configuration.ConfigurationProvider;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Observable;
import io.reactivex.Single;*/

//@Reusable
public class VehicleCapabilitiesManager implements VehicleCapabilitiesManagerInterface {

/*    private final LiveTrafficEligibilityProvider liveTrafficEligibilityProvider;
    private final VehicleCapabilitiesUtil vehicleCapabilitiesUtil;
    private final RemoteStartCapabilityRepository remoteStartCapabilityRepository;
    private final VcsAppLinkCapabilityProvider vcsAppLinkCapabilityProvider;
    private final RxSchedulingHelper rxSchedulingHelper;
    private final VehicleSdnTypeProvider vehicleSdnTypeProvider;
    private final ConfigurationProvider configurationProvider;
    private final SharedPrefsUtil sharedPrefsUtil;
    private final VcsRepository vcsRepository;*/

    //
//    @Inject
//    public VehicleCapabilitiesManager(LiveTrafficEligibilityProvider liveTrafficEligibilityProvider,
//                                      VehicleCapabilitiesUtil vehicleCapabilitiesUtil,
//                                      RemoteStartCapabilityRepository remoteStartCapabilityRepository,
//                                      VcsAppLinkCapabilityProvider vcsAppLinkCapabilityProvider,
//                                      RxSchedulingHelper rxSchedulingHelper,
//                                      VehicleSdnTypeProvider vehicleSdnTypeProvider,
//                                      ConfigurationProvider configurationProvider,
//                                      SharedPrefsUtil sharedPrefsUtil,
//                                      VcsRepository vcsRepository) {
//        this.liveTrafficEligibilityProvider = liveTrafficEligibilityProvider;
//        this.vehicleCapabilitiesUtil = vehicleCapabilitiesUtil;
//        this.remoteStartCapabilityRepository = remoteStartCapabilityRepository;
//        this.vcsAppLinkCapabilityProvider = vcsAppLinkCapabilityProvider;
//        this.rxSchedulingHelper = rxSchedulingHelper;
//        this.vehicleSdnTypeProvider = vehicleSdnTypeProvider;
//        this.configurationProvider = configurationProvider;
//        this.sharedPrefsUtil = sharedPrefsUtil;
//        this.vcsRepository = vcsRepository;
//    }
//
//    public Observable<Boolean> isEligibleForLiveTraffic(final String vin) {
//        return liveTrafficEligibilityProvider.isEligibleForLiveTraffic(vin);
//    }
//
//    public Observable<VehicleCapabilitiesResponse> getVehicleCapabilities() {
//        return vcsRepository.getVehicleCapabilities(sharedPrefsUtil.getCurrentVehicleVin());
//    }
//
//    public Observable<VehicleCapabilitiesResponse> getVehicleCapabilities(@NonNull String vin) {
//        return vcsRepository.getVehicleCapabilities(vin);
//    }
//
//    public Observable<Boolean> isEligibleForRemoteStart(@NonNull final String vin) {
//        return vcsRepository.getVehicleCapabilities(vin)
//                .flatMap(vehicleCapabilitiesResponse -> cacheAndGetEligibleForRemoteStart(vin, vehicleCapabilitiesResponse))
//                .onErrorResumeNext(getEligibleForRemoteStart(vin));
//    }
//
//    public Observable<String> getVhaTypeFromVehicleCapabilityService(@NonNull final String vin) {
//        return vcsAppLinkCapabilityProvider.getVhaTypeFromVehicleCapabilityService(vin);
//    }
//
//    public Single<SdnType> getVehicleSdnType(String vin) {
//        if (vehicleSdnTypeProvider.getVehicleSdnType(vin) != SdnType.UNKNOWN) {
//            return Single.just(vehicleSdnTypeProvider.getVehicleSdnType(vin));
//        }
//        return vcsRepository.getVehicleCapabilities(vin)
//                .take(1)
//                .ignoreElements()
//                .andThen(Single.fromCallable(() -> vehicleSdnTypeProvider.getVehicleSdnType(vin)));
//    }
//
//    public boolean isValidVcsResponse(VehicleCapabilitiesResponse response) {
//        return response != null && response.getResult() != null
//                && response.getResult().getFeatures() != null;
//    }
//
//    public boolean getFeatureEligibility(List<Feature> features, String requiredFeature) {
//        if (features == null) {
//            return false;
//        }
//        for (Feature feature : features) {
//            if (feature.getFeature().equals(requiredFeature) && feature.getState().getEligible()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public Feature getVcsFeature(List<Feature> features, String requiredFeature) {
//        if (features == null) {
//            return null;
//        }
//        for (Feature feature : features) {
//            if (feature.getFeature().equals(requiredFeature)) {
//                return feature;
//            }
//        }
//        return null;
//    }
//
//    public boolean isPhevVehicle(List<Feature> features) {
//        Feature feature = getVcsFeature(features, Feature.FeatureNames.EV_FUEL);
//        if (feature != null) {
//            if (feature.getState().getEligible() && feature.getState().getActions() != null)
//                for (VcsAction vcsAction : feature.getState().getActions()) {
//                    if (vcsAction.getType().equalsIgnoreCase(VcsAction.ActionNames.PHEV)) {
//                        return true;
//                    }
//                }
//        }
//        return false;
//    }
//
//    public boolean isBevVehicle(List<Feature> features) {
//        return configurationProvider.getConfiguration().isBevEnabled()
//                && getBevCapabilityFromVcs(features);
//    }
//
//    private boolean getBevCapabilityFromVcs(List<Feature> features) {
//        Feature feature = getVcsFeature(features, Feature.FeatureNames.EV_FUEL);
//        if (feature != null) {
//            if (feature.getState().getEligible() && feature.getState().getActions() != null)
//                for (VcsAction vcsAction : feature.getState().getActions()) {
//                    if (vcsAction.getType().equalsIgnoreCase(VcsAction.ActionNames.BEV)) {
//                        return true;
//                    }
//                }
//        }
//        return false;
//    }
//
//    private Observable<Boolean> cacheAndGetEligibleForRemoteStart(String vin, VehicleCapabilitiesResponse vehicleCapabilitiesResponse) {
//        return Observable.fromCallable(() -> {
//            vehicleCapabilitiesUtil.cacheRemoteStartCapability(vin, vehicleCapabilitiesResponse);
//            return remoteStartCapabilityRepository.isEligibleForRemoteStart(vin);
//        }).compose(rxSchedulingHelper.observableSchedulers(Threads.IO));
//    }
//
//    private Observable<Boolean> getEligibleForRemoteStart(String vin) {
//        return Observable.fromCallable(() -> remoteStartCapabilityRepository.isEligibleForRemoteStart(vin))
//                .compose(rxSchedulingHelper.observableSchedulers(Threads.IO));
//    }
//}
}
