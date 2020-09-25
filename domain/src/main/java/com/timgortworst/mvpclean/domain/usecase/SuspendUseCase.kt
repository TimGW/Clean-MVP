package com.timgortworst.mvpclean.domain.usecase

// One-shot Requests
interface SuspendUseCase<in Params, out T> {
    suspend fun execute(params: Params) : T
}