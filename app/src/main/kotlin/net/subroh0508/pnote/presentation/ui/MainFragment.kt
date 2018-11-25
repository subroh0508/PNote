package net.subroh0508.pnote.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import net.subroh0508.pnote.R
import net.subroh0508.pnote.auth.domain.entity.User
import net.subroh0508.pnote.auth.domain.usecase.GetCurrentUserUseCaseImpl
import net.subroh0508.pnote.auth.infra.api.FirebaseApiImpl
import net.subroh0508.pnote.auth.infra.repository.FirebaseAuthRepository
import net.subroh0508.pnote.common.extensions.launchWithAuth
import net.subroh0508.pnote.common.ui.fragment.RequireAuthFragment
import net.subroh0508.pnote.presentation.MainContract
import net.subroh0508.pnote.presentation.presenter.MainPresenter

class MainFragment : RequireAuthFragment(), MainContract.View {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    internal lateinit var presenter: MainContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val firebaseApi = FirebaseApiImpl(FirebaseAuth.getInstance())
        val firebaseAuthRepository = FirebaseAuthRepository(firebaseApi)
        presenter = MainPresenter(this, GetCurrentUserUseCaseImpl(firebaseAuthRepository))
        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()

        launchWithAuth {
            presenter.getCurrentUser()
        }
    }

    override fun bindUser(user: User) {
        Toast.makeText(context, "user:${user.email}", Toast.LENGTH_LONG).show()
    }
}
