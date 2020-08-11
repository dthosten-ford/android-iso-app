package com.ford.ngsdnuser.providers;

import io.reactivex.Completable;

public interface AccountInfoProviderInterface {
    Completable getAccountCountry();
}
