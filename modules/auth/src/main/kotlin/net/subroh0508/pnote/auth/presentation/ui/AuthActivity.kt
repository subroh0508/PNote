package net.subroh0508.pnote.auth.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.auth_activity.*
import kotlinx.coroutines.launch
import net.subroh0508.pnote.auth.R
import net.subroh0508.pnote.auth.domain.entity.User
import net.subroh0508.pnote.auth.domain.usecase.UserSignInUseCaseImpl
import net.subroh0508.pnote.auth.domain.usecase.UserSignOutUseCaseImpl
import net.subroh0508.pnote.auth.domain.usecase.UserSignUpUseCaseImpl
import net.subroh0508.pnote.auth.infra.api.FirebaseApiImpl
import net.subroh0508.pnote.auth.infra.repository.FirebaseAuthRepository
import net.subroh0508.pnote.auth.presentation.AuthContract
import net.subroh0508.pnote.auth.presentation.presenter.AuthPresenter
import net.subroh0508.pnote.common.ui.activity.ScopedActivity

class AuthActivity : ScopedActivity(), AuthContract.View {
    internal lateinit var presenter: AuthPresenter

    private fun setPresenter() {
        val firebaseApi = FirebaseApiImpl(FirebaseAuth.getInstance())
        val firebaseAuthRepository = FirebaseAuthRepository(firebaseApi)

        presenter = AuthPresenter(
            this,
            UserSignUpUseCaseImpl(firebaseAuthRepository),
            UserSignInUseCaseImpl(firebaseAuthRepository),
            UserSignOutUseCaseImpl(firebaseAuthRepository)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.auth_activity)
    }

    override fun onResume() {
        super.onResume()

        setPresenter()

        signIn.setOnClickListener {
            launch {
                presenter.onClickSignIn(email.text.toString(), password.text.toString())
            }
        }
        signUp.setOnClickListener {
            launch {
                presenter.onClickSignUp(email.text.toString(), password.text.toString())
            }
        }
        signOut.setOnClickListener {
            launch {
                presenter.onClickSignOut()
            }
        }
    }

    override fun onSuccess(user: User) {
        Toast.makeText(this, "Sign In: ${listOf(user.id.value, user.email).joinToString("/")}", Toast.LENGTH_LONG)
            .show()

        signIn.visibility = View.GONE
        signUp.visibility = View.GONE
        signOut.visibility = View.VISIBLE
    }

    override fun onFailure(e: Throwable) {
        Toast.makeText(this, "Sign In: Failure/${e.message}", Toast.LENGTH_LONG)
            .show()
    }

    override fun enableSignIn() {
        signIn.visibility = View.VISIBLE
        signUp.visibility = View.VISIBLE
        signOut.visibility = View.GONE
    }
}