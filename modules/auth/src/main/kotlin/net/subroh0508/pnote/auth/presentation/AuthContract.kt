package net.subroh0508.pnote.auth.presentation

import net.subroh0508.pnote.auth.domain.entity.User
import net.subroh0508.pnote.common.mvp.BaseContract

interface AuthContract {
    interface View : BaseContract.View {
        fun onSuccess(user: User)

        fun onFailure(e: Throwable)

        fun enableSignIn()
    }

    interface Presenter: BaseContract.Presenter {
        suspend fun onClickSignIn(email: String, password: String)

        suspend fun onClickSignUp(email: String, password: String)

        fun onClickSignOut()
    }
}