/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */
package com.fordmps.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.fordmps.mobileapp.shared.OpenForTesting
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

@OpenForTesting
open class BaseLifecycleViewModel : ViewCallbackObserver {

        companion object {
        private val lifecycleSubscriptions = CompositeDisposable()
        private val onHiddenDisposables = CompositeDisposable()
        }

        open var viewCallbackEmitter: ViewCallbackEmitter? = null
        set(viewCallbackEmitter) {
        field = viewCallbackEmitter
        this.viewCallbackEmitter?.addObserver(this)
        }

        protected open infix fun subscribeOnLifecycle(disposable: Disposable?) {
        disposable?.let { lifecycleSubscriptions.add(it) }
        }

        protected infix fun disposeOnHidden(disposable: Disposable) {
        onHiddenDisposables.add(disposable)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        open fun onPause() {
        onHiddenDisposables.clear()
        lifecycleSubscriptions.clear()
        }

        override fun onHidden() = onHiddenDisposables.clear()

        // After onPermissionsResult(), onResume() is called. so to prevent re-registering events.
        override fun onPermissionsResult(result: PermissionResult) = lifecycleSubscriptions.clear()
        }