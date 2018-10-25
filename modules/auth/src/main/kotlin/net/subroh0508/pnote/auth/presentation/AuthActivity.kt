package net.subroh0508.pnote.auth.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.auth_activity.*
import net.subroh0508.pnote.auth.R

class AuthActivity : AppCompatActivity() {
    internal val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    internal val authListener: FirebaseAuth.AuthStateListener by lazy {
        FirebaseAuth.AuthStateListener {
            val user = it.currentUser

            if (user == null) {
                signIn.visibility = View.VISIBLE
                signOut.visibility = View.GONE
            } else {
                signIn.visibility = View.GONE
                signOut.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.auth_activity)
    }

    override fun onStart() {
        super.onStart()

        firebaseAuth.addAuthStateListener(authListener)
    }

    override fun onStop() {
        super.onStop()

        firebaseAuth.removeAuthStateListener(authListener)
    }

    override fun onResume() {
        super.onResume()

        signUp.setOnClickListener { createUser(email.text.toString(), password.text.toString()) }
        signIn.setOnClickListener { signInUser(email.text.toString(), password.text.toString()) }
        signOut.setOnClickListener { signOutUser() }
    }

    private fun createUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Toast.makeText(this, "create result: ${it.isSuccessful}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun signInUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Toast.makeText(this, "sign in result: ${it.isSuccessful}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun signOutUser() {
        firebaseAuth.signOut()
    }
}