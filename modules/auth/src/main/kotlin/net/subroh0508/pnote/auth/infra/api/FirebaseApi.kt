package net.subroh0508.pnote.auth.infra.api

import com.google.firebase.auth.FirebaseUser

interface FirebaseApi {
    fun getCurrentUser(): FirebaseUser?
    suspend fun signUp(email: String, password: String): FirebaseUser
    suspend fun signIn(email: String, password: String): FirebaseUser
    fun signOut()
}