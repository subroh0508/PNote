package net.subroh0508.pnote.presentation

import net.subroh0508.pnote.auth.domain.entity.User
import net.subroh0508.pnote.common.mvp.BaseContract

interface MainContract {
    interface View : BaseContract.View {
        fun bindUser(user: User)
    }

    interface Presenter : BaseContract.Presenter {
        suspend fun getCurrentUser()
    }

    interface ViewModel
}