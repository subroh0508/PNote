package net.subroh0508.pnote.auth.infra.api

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseApiImpl(private val firebaseAuth: FirebaseAuth) : FirebaseApi {
    override fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    override suspend fun signIn(email: String, password: String): FirebaseUser = suspendCoroutine { continuation ->
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(getOnCompleteListener(continuation))
    }

    override suspend fun signUp(email: String, password: String): FirebaseUser = suspendCoroutine { continuation ->
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(getOnCompleteListener(continuation))
    }

    override fun signOut() = firebaseAuth.signOut()

    private fun getOnCompleteListener(continuation: Continuation<FirebaseUser>) = OnCompleteListener<AuthResult> {
        val exception = it.exception
        val user = it.result?.user

        when {
            it.isSuccessful && user != null -> continuation.resume(user)
            it.isCanceled -> continuation.resumeWithException(CancellationException())
            exception != null -> continuation.resumeWithException(exception)
        }
    }
}