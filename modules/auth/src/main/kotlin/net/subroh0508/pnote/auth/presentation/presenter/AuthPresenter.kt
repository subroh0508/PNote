package net.subroh0508.pnote.auth.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import net.subroh0508.pnote.auth.domain.usecase.UserSignInUseCase
import net.subroh0508.pnote.auth.domain.usecase.UserSignOutUseCase
import net.subroh0508.pnote.auth.domain.usecase.UserSignUpUseCase
import net.subroh0508.pnote.auth.presentation.AuthContract

class AuthPresenter(
    private val view: AuthContract.View,
    private val signUpUseCase: UserSignUpUseCase,
    private val signInUseCase: UserSignInUseCase,
    private val signOutUseCase: UserSignOutUseCase
) : AuthContract.Presenter {
    override suspend fun onClickSignIn(email: String, password: String) = coroutineScope {
        try {
            val user = async(Dispatchers.Default) { signInUseCase.execute(email, password) }.await()

            view.onSuccess(user)
        } catch (e: Throwable) {
            view.onFailure(e)
        }
    }

    override suspend fun onClickSignUp(email: String, password: String) = coroutineScope {
        try {
            val user = async(Dispatchers.Default) { signUpUseCase.execute(email, password) }.await()

            view.onSuccess(user)
        } catch (e: Throwable) {
            view.onFailure(e)
        }
    }

    override fun onClickSignOut() {
        signOutUseCase.execute()

        view.enableSignIn()
    }
}