package net.subroh0508.pnote.auth.presentation.presenter

import kotlinx.coroutines.launch
import net.subroh0508.pnote.auth.domain.usecase.UserSignInUseCase
import net.subroh0508.pnote.auth.domain.usecase.UserSignOutUseCase
import net.subroh0508.pnote.auth.domain.usecase.UserSignUpUseCase
import net.subroh0508.pnote.auth.presentation.AuthContract
import net.subroh0508.pnote.common.extensions.asyncOnNewThread

class AuthPresenter(
    private val view: AuthContract.View,
    private val signUpUseCase: UserSignUpUseCase,
    private val signInUseCase: UserSignInUseCase,
    private val signOutUseCase: UserSignOutUseCase
) : AuthContract.Presenter {
    override fun onClickSignIn(email: String, password: String) = view.launch {
        try {
            val user = asyncOnNewThread {
                signInUseCase.execute(email, password)
            }.await()

            view.onSuccess(user)
        } catch (e: Throwable) {
            view.onFailure(e)
        }
    }

    override fun onClickSignUp(email: String, password: String) = view.launch {
        try {
            val user = asyncOnNewThread {
                signUpUseCase.execute(email, password)
            }.await()

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