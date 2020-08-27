package io.reactivex.schedulers

import io.reactivex.Scheduler
import io.reactivex.plugins.RxJavaPlugins

public final class   AndroidSchedulers {
//    public static fun mainThread(): Scheduler?
//        return RxJavaPlugins.onComputationScheduler(Schedulers.COMPUTATION)
//    }

fun mainThread(): Scheduler? {
    return RxJavaPlugins.onComputationScheduler(Schedulers.COMPUTATION)
}
}
