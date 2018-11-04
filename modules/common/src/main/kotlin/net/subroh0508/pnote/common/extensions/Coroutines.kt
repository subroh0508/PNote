package net.subroh0508.pnote.common.extensions

import kotlinx.coroutines.*
import net.subroh0508.pnote.common.exception.TokenExpireException
import net.subroh0508.pnote.common.mvp.RequireAuthContract

fun <T> CoroutineScope.asyncOnNewThread(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = async(Dispatchers.Default, start, block)

fun <T> CoroutineScope.asyncWithAuth(
    presenter: RequireAuthContract.Presenter,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    val handler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is TokenExpireException -> presenter.startWelcomeActivity()
        }
    }

    return async(Dispatchers.Default + handler, start, block)
}