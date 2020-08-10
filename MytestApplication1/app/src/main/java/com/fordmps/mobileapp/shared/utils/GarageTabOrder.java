/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */


package com.fordmps.mobileapp.shared.utils;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface GarageTabOrder {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({VEHICLE, EMPTY_GARAGE, SHOP_VEHICLE, CAR_SHARE, BIKE_SHARE})
    @interface TabOrder {
    }

    int VEHICLE = 10;
    int CAR_SHARE = 3;
    int BIKE_SHARE = 2;
    int EMPTY_GARAGE = 1;
    int SHOP_VEHICLE = 0;
}