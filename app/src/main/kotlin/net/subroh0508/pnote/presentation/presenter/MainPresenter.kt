package net.subroh0508.pnote.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import net.subroh0508.pnote.auth.domain.usecase.GetCurrentUserUseCase
import net.subroh0508.pnote.common.exception.TokenExpireException
import net.subroh0508.pnote.presentation.MainContract

class MainPresenter(
    private val view: MainContract.View,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : MainContract.Presenter {
    override suspend fun getCurrentUser() = coroutineScope {
        val currentUser = async(Dispatchers.Default) {
            getCurrentUserUseCase.execute() ?: throw TokenExpireException()
        }.await()

        view.bindUser(currentUser)
    }
}