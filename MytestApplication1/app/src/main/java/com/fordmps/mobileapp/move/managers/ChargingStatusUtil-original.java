///*
// * CONFIDENTIAL FORD MOTOR COMPANY
// * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
// * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
// * Copyright 2019, Ford Motor Company.
// *
// */
//
//package com.fordmps.mobileapp.move.managers;
//
//public class ChargingStatusUtil implements ChargingStatusUtilInterface {
////    public static final int CHARGING_STATUS_RESOURCE = -1;
////    private final ResourceProvider resourceProvider;
////
////    @Inject
////    public ChargingStatusUtil(ResourceProvider resourceProvider) {
////        this.resourceProvider = resourceProvider;
////    }
////
////    public boolean shouldShowHybridMode(VehicleStatus vehicleStatus) {
////        return (vehicleStatus.getHybridModeStatus().isPresent())
////                && (vehicleStatus.getChargingStatus().isPresent())
////                && AUTO_CHARGE_SUSTAIN_MODE.equals(vehicleStatus.getHybridModeStatus().get())
////                && (NOT_READY_NULL_STATE.equals(vehicleStatus.getChargingStatus().get().toLowerCase())
////                || NOT_READY.equals(vehicleStatus.getChargingStatus().get().toLowerCase()));
////    }
////
////    public boolean shouldShowChargingStatus(VehicleStatus vehicleStatus) {
////        return vehicleStatus.getChargingStatus().isPresent()
////                && vehicleStatus.getPlugStatusValue().isPresent();
////    }
////
////    public boolean shouldShowUnplugged(VehicleStatus vehicleStatus) {
////        return vehicleStatus.getPlugStatusValue().isPresent()
////                && PLUG_OFF.equals(getPlugStatus(vehicleStatus.getPlugStatusValue().get()));
////    }
////
////    public boolean isInProgressOrFastCharging(VehicleStatus vehicleStatus) {
////        if (!vehicleStatus.getChargingStatus().isPresent()) {
////            return false;
////        }
////        String chargingStatus = vehicleStatus.getChargingStatus().get().toLowerCase();
////        return (vehicleStatus.getPlugStatusValue().isPresent()
////                && vehicleStatus.getChargingStatus().isPresent()
////                && PLUG_ON.equals(getPlugStatus(vehicleStatus.getPlugStatusValue().get()))
////                && (IN_PROGRESS.equals(chargingStatus) || FAST_CHARGING.equals(chargingStatus) || NOT_USED.equals(chargingStatus)));
////    }
////
////    public String getChargingStatus(VehicleStatus vehicleStatus) {
////        String chargingStatus = vehicleStatus.getChargingStatus().get().toLowerCase();
////        int chargingStatusResource;
////        switch (chargingStatus) {
////            case COMPLETE:
////            case CHARGE_TARGET_REACHED:
////                chargingStatusResource = R.string.move_ev_vehiclelanding_chargestatus_status_charge_reached_landing;
////                break;
////            case EVES_PAUSED:
////                chargingStatusResource = R.string.move_ev_vehiclelanding_chargestatus_status_charge_station_paused;
////                break;
////            case SCHEDULED:
////            case CHARGE_SCHEDULED:
////            case NOT_READY:
////            case NOT_READY_NULL_STATE:
////                chargingStatusResource = R.string.move_ev_vehiclelanding_chargestatus_status_plugged_in;
////                break;
////            case CABIN_PRE_CONDITIONING:
////                chargingStatusResource = R.string.move_ev_vehiclelanding_chargestatus_status_preconditioning_landing;
////                break;
////            case FAULT_UNKNOWN_LOCATION:
////            case FAULT_INSIDE_CAR:
////            case FAULT_OUTSIDE_CAR:
////                chargingStatusResource = R.string.move_ev_vehiclelanding_chargestatus_status_charge_fault_landing;
////                break;
////            case EVES_NOT_COMPATIBLE:
////                chargingStatusResource = R.string.move_ev_vehiclelanding_chargestatus_status_not_compatible_landing;
////                break;
////            case EVES_NOT_DETECTED:
////                chargingStatusResource = R.string.move_ev_vehiclelanding_chargestatus_status_not_detected_landing;
////                break;
////            case IN_PROGRESS:
////            case CHARGING_AC:
////                chargingStatusResource = R.string.move_ev_vehiclelanding_chargestatus_status_charging;
////                break;
////            case FAST_CHARGING:
////            case CHARGING_DC_FAST_CHARGE:
////                chargingStatusResource = R.string.move_ev_vehiclelanding_chargestatus_status_fast_charging;
////                break;
////            default:
////                chargingStatusResource = CHARGING_STATUS_RESOURCE;
////        }
////        return chargingStatusResource != CHARGING_STATUS_RESOURCE ? resourceProvider.getString(chargingStatusResource) : "";
////    }
////
////    public @DrawableRes
////    int getChargingIcon(VehicleStatus vehicleStatus) {
////        int chargingIconResource;
////        String chargingStatus = vehicleStatus.getChargingStatus().get().toLowerCase();
////        switch (chargingStatus) {
////            case COMPLETE:
////            case IN_PROGRESS:
////            case CHARGING_AC:
////            case FAST_CHARGING:
////            case CHARGING_DC_FAST_CHARGE:
////            case CABIN_PRE_CONDITIONING:
////            case EVES_PAUSED:
////                chargingIconResource = R.drawable.ic_ev_charging;
////                break;
////            case SCHEDULED:
////            case CHARGE_SCHEDULED:
////            case NOT_READY:
////            case NOT_READY_NULL_STATE:
////            case CHARGE_TARGET_REACHED:
////                chargingIconResource = R.drawable.ic_ev_plugged_not_charging;
////                break;
////            case EVES_NOT_DETECTED:
////                chargingIconResource = R.drawable.ic_ev_unplugged;
////                break;
////            case FAULT_UNKNOWN_LOCATION:
////            case FAULT_INSIDE_CAR:
////            case FAULT_OUTSIDE_CAR:
////            case EVES_NOT_COMPATIBLE:
////                chargingIconResource = R.drawable.ic_ev_unplugged_error;
////                break;
////            default:
////                chargingIconResource = CHARGING_STATUS_RESOURCE;
////        }
////        return chargingIconResource;
////    }
////
////    private String getPlugStatus(Integer plugstatus) {
////        if (plugstatus != null) {
////            return (plugstatus == 1 ? PLUG_ON : PLUG_OFF);
////        }
////        return "";
////    }
//}
