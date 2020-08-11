
package com.fordmps.core;

import androidx.core.content.PermissionChecker

public interface ViewCallbackObserver {
    fun onHidden()
    fun onPermissionsResult(result: PermissionChecker.PermissionResult)
}