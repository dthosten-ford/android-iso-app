package com.ford.rxutils.schedulers;
import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({Threads.IO, Threads.THREAD_POOL, Threads.COMPUTATION})
@Retention(RetentionPolicy.SOURCE)
public @interface Threads {

    int IO = 1;
    int COMPUTATION = 2;
    int THREAD_POOL = 3;
}
