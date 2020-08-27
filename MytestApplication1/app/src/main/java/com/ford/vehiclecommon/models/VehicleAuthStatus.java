/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.vehiclecommon.models;

import com.google.common.base.Optional;

public interface VehicleAuthStatus {

    boolean isAuthorized();

    boolean isPreAuthorized();

    boolean isAuthorizationPending();

    boolean isSecondaryAuthPending();

    boolean hasAuthorizationStatus();

    boolean isPendingReset();

    default Optional<Vehicle.Authorization> getAuthorization() {
        return Optional.absent();
    }

    default String getAuthorizationRawValue() {
        return "";
    }

    default void setAuthorizationRawValue(String authorization) {
    }

    class AuthorizationStates {
        public static final String AUTHORIZED = "AUTHORIZED";
        public static final String NOT_AUTHORIZED = "NOTAUTHORIZED";
        public static final String PENDING_APPROVAL = "PENDING";
        public static final String INITAUTHSTARTED = "INITAUTHSTARTED";
        public static final String INITAUTHCOMPLETE = "INITAUTHCOMPLETE";
        public static final String FINALAUTHSTARTED = "FINALAUTHSTARTED";
        public static final String INITTAKEOVERAUTHSTARTED = "INITTAKEOVERAUTHSTARTED";
        public static final String INITTAKEOVERAUTHCOMPLETE = "INITTAKEOVERAUTHCOMPLETE";
        public static final String FINALTAKEOVERAUTHSTARTED = "FINALTAKEOVERAUTHSTARTED";
    }
}
