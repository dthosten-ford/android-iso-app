package com.ford.rxutils.schedulers;

import io.reactivex.ObservableTransformer;

public interface RxSchedulingHelperInterface<T> {
    ObservableTransformer<T, T> observableSchedulers();
}
