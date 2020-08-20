package com.ford.applink.managers;

import io.reactivex.Completable;

public interface ActiveVhaAlertsManagerInterface {
    Completable getActiveAlertsFromCacheThenNetwork(String vin, String source);
    // Completable getActiveAlertsFromCacheThenNetwork(String vin, String source);
}
