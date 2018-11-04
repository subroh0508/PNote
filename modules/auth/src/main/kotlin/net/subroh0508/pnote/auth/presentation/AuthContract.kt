package net.subroh0508.pnote.auth.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import net.subroh0508.pnote.auth.domain.entity.User
import net.subroh0508.pnote.common.mvp.BaseContract

interface AuthContract {
    interface View : BaseContract.View, CoroutineScope {
        fun onSuccess(user: User)

        fun onFailure(e: Throwable)

        fun enableSignIn()
    }

    interface Presenter: BaseContract.Presenter {
        fun onClickSignIn(email: String, password: String): Job

        fun onClickSignUp(email: String, password: String): Job

        fun onClickSignOut()
    }
}