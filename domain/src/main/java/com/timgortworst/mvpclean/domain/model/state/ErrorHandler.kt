package com.timgortworst.mvpclean.domain.model.state

import com.timgortworst.mvpclean.domain.model.state.ErrorEntity

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
    fun getError(statusCode: Int, throwable: Throwable? = null): ErrorEntity
}