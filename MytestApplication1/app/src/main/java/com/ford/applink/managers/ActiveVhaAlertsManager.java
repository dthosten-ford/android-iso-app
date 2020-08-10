/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.applink.managers;

import androidx.core.util.Pair;

import com.ford.locale.ServiceLocaleProvider;
import com.ford.ngsdnvehicle.models.ActiveAlertVinData;
import com.ford.rxutils.DelayActionUtil;
import com.ford.rxutils.schedulers.RxSchedulingHelper;
import com.ford.rxutils.schedulers.Threads;
import com.ford.vehiclehealth.models.NgsdnActiveAlertListRequest;
import com.ford.vehiclehealth.models.NgsdnActiveAlertListResponse;
import com.ford.vehiclehealth.models.VehicleAlertResponse;
import com.ford.vehiclehealth.providers.VehicleHealthProvider;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static com.ford.ngsdnvehicle.models.ActiveAlertVinData.*;

//@Singleton
//public class ActiveVhaAlertsManager implements ActiveVhaAlertsManagerInterface {
//
//    private static final long DELAY_IN_SECONDS = 30;
//    private static final int MAX_RETRY_TIMES = 2;
//
//    private final VehicleHealthProvider vehicleHealthProvider;
//    private final RxSchedulingHelper rxSchedulingHelper;
//    private final AppLinkManager appLinkManager;
//    private final ServiceLocaleProvider serviceLocaleProvider;
//    private final DelayActionUtil delayActionUtil;
//
//    private PublishSubject<VehicleAlertResponse> activeAlertPublishSubject = PublishSubject.create();
//
//    public ActiveVhaAlertsManager(VehicleHealthProvider vehicleHealthProvider,
//                                  AppLinkManager appLinkManager,
//                                  RxSchedulingHelper rxSchedulingHelper,
//                                  ServiceLocaleProvider serviceLocaleProvider,
//                                  DelayActionUtil delayActionUtil) {
//        this.vehicleHealthProvider = vehicleHealthProvider;
//        this.rxSchedulingHelper = rxSchedulingHelper;
//        this.appLinkManager = appLinkManager;
//        this.serviceLocaleProvider = serviceLocaleProvider;
//        this.delayActionUtil = delayActionUtil;
//
//        this.vehicleHealthProvider.getApplinkSetAlertsObservable().subscribe(this::setHealthAlertsToApplink, Throwable::printStackTrace);
//        this.appLinkManager.getAppLinkRefreshActiveAlertsObservable().subscribe(this::getActiveAlertsAndUpdateUiIfNecessary, Throwable::printStackTrace);
//        this.appLinkManager.getAppLinkRefreshHmiAlertsObservable().subscribe(this::getActiveAlertsAndUpdateApplink, Throwable::printStackTrace);
//    }
//
//    public Observable<VehicleAlertResponse> getActiveAlertPassiveObservable(String vin) {
//        return activeAlertPublishSubject.hide()
//                .filter(vehicleAlertResponse -> vehicleAlertResponse.getVin().equals(vin));
//    }
//
//    public Observable<VehicleAlertResponse> getActiveAlertsFromCacheThenNetwork(final String vin, String source) {
//        return vehicleHealthProvider.getActiveAlertsOneVehicle(getNgsdnActiveAlertListRequest(vin, source))
//                .filter(response -> response.getVehicleAlertResponseList() != null && !response.getVehicleAlertResponseList().isEmpty())
//                .map(vehicleAlertList -> vehicleAlertList.getVehicleAlertResponseList().get(0))
//                .compose(rxSchedulingHelper.observableSchedulers(Threads.IO));
//    }
//
//    private void getActiveAlertsAndUpdateUiIfNecessary(final String vin) {
//        delayActionUtil.repeatAfterDelay(DELAY_IN_SECONDS, TimeUnit.SECONDS, MAX_RETRY_TIMES)
//                .flatMapCompletable(times -> vehicleHealthProvider.getActiveAlertsFromNetwork(getNgsdnActiveAlertListRequest(vin, SOURCE_APPLINK))
//                        .zipWith(vehicleHealthProvider.getActiveAlertsTimestampFromCache(vin), Pair::create)
//                        .filter(pair -> isTimestampsValidAndDifferent(pair))
//                        .map(pair -> pair.first)
//                        .flatMapCompletable(this::updateAlerts))
//                .subscribe(() -> {
//                }, Throwable::printStackTrace);
//    }
//
//    private void getActiveAlertsAndUpdateApplink(String vin) {
//        vehicleHealthProvider.getActiveAlertsFromNetwork(getNgsdnActiveAlertListRequest(vin, SOURCE_APPLINK))
//                .filter(response -> !response.getVehicleAlertResponseList().isEmpty())
//                .subscribe(this::updateAlerts, Throwable::printStackTrace);
//    }
//
//    private NgsdnActiveAlertListRequest getNgsdnActiveAlertListRequest(String vin, String source) {
//        List<ActiveAlertVinData> activeAlertVinDataList = new ArrayList<>();
//        activeAlertVinDataList.add(new ActiveAlertVinData(vin, source));
//        return new NgsdnActiveAlertListRequest(serviceLocaleProvider.getApplicationDisplayLanguage(), appLinkManager.getHmiLanguage(), activeAlertVinDataList);
//    }
//
//    private CompletableSource updateAlerts(NgsdnActiveAlertListResponse ngsdnActiveAlertListResponse) {
//        vehicleHealthProvider.updateActiveAlertCacheOneVehicle(ngsdnActiveAlertListResponse);
//        setHealthAlertsToApplink(ngsdnActiveAlertListResponse);
//        activeAlertPublishSubject.onNext(ngsdnActiveAlertListResponse.getVehicleAlertResponseList().get(0));
//        return Completable.complete();
//    }
//
//    private boolean isTimestampsValidAndDifferent(Pair<NgsdnActiveAlertListResponse, String> pair) {
//        List<VehicleAlertResponse> alertResponses = pair !=null ? pair.first.getVehicleAlertResponseList() : null;
//        return alertResponses != null ?
//                alertResponses.get(0).getEventTimeStamp() != null ?
//                      !alertResponses.get(0).getEventTimeStamp().equals(pair.second):false:false;
//    }
//
//    private void setHealthAlertsToApplink(NgsdnActiveAlertListResponse response) {
//        try {
//            String vin = response.getVehicleAlertResponseList().get(0).getVin();
//            if (appLinkManager.isVehicleConnected(vin)) {
//                Gson gson = new Gson();
//                String jsonString = gson.toJson(response, NgsdnActiveAlertListResponse.class);
//                JSONObject mainObject = new JSONObject(jsonString);
//                appLinkManager.setHealthAlerts(mainObject);
//            }
//        } catch (JSONException ignored) {
//        }
//    }
//}