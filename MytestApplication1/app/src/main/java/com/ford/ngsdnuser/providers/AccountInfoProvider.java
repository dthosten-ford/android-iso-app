package com.ford.ngsdnuser.providers;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface AccountInfoProvider {
    Observable<String> getAccountCountry();
}
