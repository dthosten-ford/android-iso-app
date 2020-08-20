package com.ford.ngsdnuser.providers;

import io.reactivex.Completable;

public interface AccountInfoProvider {
    Completable getAccountCountry();
}
