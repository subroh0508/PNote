package net.subroh0508.pnote.common.extensions

import kotlinx.coroutines.*
import net.subroh0508.pnote.common.exception.RequireUpdateException
import net.subroh0508.pnote.common.exception.TokenExpireException
import net.subroh0508.pnote.common.ui.activity.RequireAuthActivity
import net.subroh0508.pnote.common.ui.activity.ValidateVersionActivity
import net.subroh0508.pnote.common.ui.fragment.RequireAuthFragment
import net.subroh0508.pnote.common.ui.fragment.ValidateVersionFragment
import kotlin.coroutines.CoroutineContext

fun ValidateVersionActivity.launchWithUpdate(
    context: CoroutineContext = Dispatchers.Default,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val handler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is RequireUpdateException -> startNotifyUpdateActivity()
        }
    }

    return launch(context + handler, start, block)
}

fun ValidateVersionFragment.launchWithUpdate(
    context: CoroutineContext = Dispatchers.Default,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val handler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is RequireUpdateException -> startNotifyUpdateActivity()
        }
    }

    return launch(context + handler, start, block)
}

fun RequireAuthActivity.launchWithAuth(
    context: CoroutineContext = Dispatchers.Default,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val handler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is RequireUpdateException -> startNotifyUpdateActivity()
            is TokenExpireException -> startWelcomeActivity()
        }
    }

    return launch(context + handler, start, block)
}

fun RequireAuthFragment.launchWithAuth(
    context: CoroutineContext = Dispatchers.Default,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val handler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is RequireUpdateException -> startNotifyUpdateActivity()
            is TokenExpireException -> startWelcomeActivity()
        }
    }

    return launch(context + handler, start, block)
}
