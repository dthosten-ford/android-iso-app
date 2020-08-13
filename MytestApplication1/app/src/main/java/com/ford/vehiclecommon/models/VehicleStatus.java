/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.vehiclecommon.models;

import androidx.annotation.Nullable;

import com.google.common.base.Optional;

import java.util.Date;

public interface VehicleStatus {
    default String getVin() {
        return "";
    }
    default String getAuthorizationString() {
        return "";
    }

    Optional<Double> getFuelLevelPercentage();
    Optional<Integer> getOilLifePercentage();
    Optional<Integer> getOdometer();
    Optional<ComponentStatus> getBatteryHealth();
    Optional<ComponentStatus> getTirePressure();
    Optional<Date> getLastRefreshDate();
    Optional<Date> getLastModifiedDate();
    Optional<Double> getLongitude();
    Optional<Double> getLatitude();
    Optional<Double> getDistanceToEmpty();

    int getRemainingStartTimeMinutes();
    int getRemoteStartExtensionDuration();

    boolean isVehicleStarted();

    default boolean isZoneLightingActivated() {
        return false;
    }

    default String getFrontZoneLight() {
        return "";
    }

    default String getRearZoneLight() {
        return "";
    }

    default String getLeftZoneLight() {
        return "";
    }

    default String getRightZoneLight() {
        return "";
    }

    default String getZoneLightingWarningStatus() {
        return "";
    }

    default String getZoneLightingShutdownWarning(){
        return "";
    }

    default String getProPowerMode() {
        return "";
    }

    default String getProPowerOutletAUsage() {
        return "0";
    }

    default String getProPowerOutletBUsage() {
        return "0";
    }

    @Nullable
    default Date getProPowerTimeStamp() { return null; }

    default String getProPowerMaxAvailablePower() {
        return "0";
    }

    default Optional<String> getTrailerLightCheckStatus() {
        return Optional.absent();
    }

    default boolean hasNewerModifiedDateThanStatus(VehicleStatus otherVehicleStatus) {
        return false;
    }

    default boolean hasNewerRefreshDateThanStatus(VehicleStatus otherStatus) {
        return false;
    }

    // region fields in EV

    default Optional<String> getBatteryFillLevelStatus() {
        return Optional.absent();
    }

    default Optional<String> getElectricVehicleDteStatus() {
        return Optional.absent();
    }

    default boolean electricVehicleDteExists() {
        return false;
    }

    default boolean batteryFillLevelExists() {
        return false;
    }

    default Optional<Double> getBatteryFillLevelPercentage() {
        return Optional.absent();
    }

    default boolean isBatteryFillLevelPercentageValid() {
        return false;
    }

    default Optional<Double> getElectricVehicleDte() {
        return Optional.absent();
    }

    default Optional<String> getChargingStatus() {
        return Optional.absent();
    }

    default Optional<Integer> getPlugStatusValue() {
        return Optional.absent();
    }

    default Optional<String> getHybridModeStatus() {
        return Optional.absent();
    }

    default Optional<Date> getChargeStartTime() {
        return Optional.absent();
    }

    default Optional<Date> getChargeEndTime() {
        return Optional.absent();
    }

    default  Optional<Integer> getFastChargeBulkTest() {
        return Optional.absent();
    }

    default Optional<Integer> getFastChargeCmpltTest() {
        return Optional.absent();
    }

    default Optional<String> getBatteryTracLowChargeThreshold() {
        return Optional.absent();
    }

    default Optional<String> getBattTracLoSocDDsply() {
        return Optional.absent();
    }

    // end region

    // region fields in TPMS

    default Optional<Double> getRecommendedFrontTirePressureValue() {
        return Optional.absent();
    }

    default Optional<String> getRecommendedFrontTirePressureStatus() {
        return Optional.absent();
    }

    default Optional<Double> getRecommendedRearTirePressureValue() {
        return Optional.absent();
    }

    default Optional<String> getRecommendedRearTirePressureStatus() {
        return Optional.absent();
    }

    default Optional<String> getLeftFrontTirePressureValue() {
        return Optional.absent();
    }

    default Optional<String> getLeftFrontTirePressureStatus() {
        return Optional.absent();
    }


    default Optional<String> getLeftFrontTireConditionStatus() {
        return Optional.absent();
    }

    default Optional<String> getRightFrontTirePressureValue() {
        return Optional.absent();
    }

    default Optional<String> getRightFrontTirePressureStatus() {
        return Optional.absent();
    }


    default Optional<String> getRightFrontTireConditionStatus() {
        return Optional.absent();
    }

    default Optional<String> getInnerLeftRearTirePressureValue() {
        return Optional.absent();
    }

    default Optional<String> getInnerLeftRearTirePressureStatus() {
        return Optional.absent();
    }


    default Optional<String> getInnerLeftRearTireConditionStatus() {
        return Optional.absent();
    }

    default Optional<String> getInnerRightRearTirePressureValue() {
        return Optional.absent();
    }

    default Optional<String> getInnerRightRearTirePressureStatus() {
        return Optional.absent();
    }


    default Optional<String> getInnerRightRearTireConditionStatus() {
        return Optional.absent();
    }

    default Optional<String> getOuterLeftRearTirePressureValue() {
        return Optional.absent();
    }

    default Optional<String> getOuterLeftRearTirePressureStatus() {
        return Optional.absent();
    }


    default Optional<String> getOuterLeftRearTireConditionStatus() {
        return Optional.absent();
    }

    default Optional<String> getOuterRightRearTirePressureValue() {
        return Optional.absent();
    }

    default Optional<String> getOuterRightRearTirePressureStatus() {
        return Optional.absent();
    }


    default Optional<String> getOuterRightRearTireConditionStatus() {
        return Optional.absent();
    }

    default Optional<Integer> getTirePressureByLocation() {
        return Optional.absent();
    }

    default Optional<String> getOutAndAboutValue() {
        return Optional.absent();
    }

    default boolean isOutAndAbout() {
        return false;
    }

    // end region

    // region CCS


    // end region

    // region FOTA/DeepSleep

    default Optional<Boolean> getFirmwareUpgradeInProgressValue() {
        return Optional.of(false);
    }

    default Optional<Boolean> getDeepSleepInProgressValue() {
        return Optional.of(false);
    }

    // end region

    // start generic region

    default Optional<Integer> getDualRearWheel() {
        return Optional.absent();
    }

    default Optional<Date> getGpsTimeStamp() {
        return Optional.absent();
    }

    default void setGpsToNull() {

    }


    // end region

    enum ComponentStatus {
        GOOD,
        BAD,
        ERROR
    }
}
