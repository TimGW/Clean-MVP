package com.timgortworst.mvpclean.presentation.extension

import kotlinx.coroutines.Job

/**
 * Cancel the Job if it's active.
 */
fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}