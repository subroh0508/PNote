package net.subroh0508.pnote.auth.infra.repository

import net.subroh0508.pnote.auth.domain.entity.User
import net.subroh0508.pnote.auth.infra.api.FirebaseApi

class FirebaseAuthRepository(private val firebaseApi: FirebaseApi) : AuthRepository {
    override fun getCurrentUser() = firebaseApi.getCurrentUser()?.let { User(it.uid, it.email ?: "no email") }

    override suspend fun signInUser(email: String, password: String): User {
        val currentUser = firebaseApi.signIn(email, password)

        return User(currentUser.uid, currentUser.email ?: "no email")
    }

    override suspend fun signUpUser(email: String, password: String): User {
        val currentUser = firebaseApi.signUp(email, password)

        return User(currentUser.uid, currentUser.email ?: "no email")
    }

    override fun signOut() = firebaseApi.signOut()
}