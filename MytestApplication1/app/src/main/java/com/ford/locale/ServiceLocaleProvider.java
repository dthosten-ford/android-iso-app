/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.locale;

import android.os.Build;

import java.util.List;
import java.util.Locale;

public interface ServiceLocaleProvider {
    ServiceLocale getLocale();
    String getDeviceLanguage();
    String getApplicationDisplayLanguage();
    String getAemDeviceLanguage();

    default Locale getApplicationLocale(Locale deviceLocale, Locale defaultLocale, List<Locale> regionSupportedLocales) {
        for (Locale supportedLocale : regionSupportedLocales) {
            if (deviceLocale.toString().equals(supportedLocale.toString()))
                return supportedLocale;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            for (Locale supportedLocale : regionSupportedLocales) {
                if (deviceLocale.getLanguage().equals(supportedLocale.getLanguage()))
                    return supportedLocale;
            }
        }
        return defaultLocale;
    }

    default Locale getSystemLocale() {
        return Locale.getDefault();
    }
}
