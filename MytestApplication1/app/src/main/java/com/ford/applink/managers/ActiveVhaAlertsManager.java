package com.ford.applink.managers;

import io.reactivex.Completable;

public interface ActiveVhaAlertsManager {
    Completable getActiveAlertsFromCacheThenNetwork(String vin, String source);
    // Completable getActiveAlertsFromCacheThenNetwork(String vin, String source);
}
