/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.vehiclecommon.models;

import androidx.annotation.NonNull;

import java.util.Optional;


public interface Vehicle {

    String getVin();
    @NonNull
    Optional<String> getNickname();
    boolean isTcuEnabled();
    boolean isPreAuthorized();
    boolean isAuthorized();
    boolean isPendingAuthorization();
    boolean isSecondaryAuthPending();
    @NonNull
    Optional<Authorization> getAuthorization();
    String getModelYear();
    String getModelName();
    @NonNull
    Optional<String> getLocalizedModelName();
    String getJointVenture();

//    @NonNull
//    Optional<VehicleDetails> getVehicleDetails();
    @NonNull
    Optional<VehicleStatus> getVehicleStatus();
    @NonNull
    Optional<String> getVehicleType();
    @NonNull
    Optional<String> getColor();
    @NonNull
    Optional<String> getModelCode();

    int SOURCE_ASDN = 0;
    int SOURCE_NGSDN = 1;
    int SOURCE_TMC = 2;

    @VehicleSource
    int getSDNSourceForTCU(); // for use by wrapper managers

    enum Authorization {
        UNAUTHORIZED,
        PRIMARY_AUTH_PENDING,
        SECONDARY_AUTH_PENDING,
        PRE_AUTHORIZED,
        AUTHORIZED
    }
}