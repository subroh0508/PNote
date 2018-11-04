package net.subroh0508.pnote.auth.infra.repository

import net.subroh0508.pnote.auth.domain.entity.User

interface AuthRepository {
    fun getCurrentUser(): User?

    suspend fun signUpUser(email: String, password: String): User

    suspend fun signInUser(email: String, password: String): User

    fun signOut()
}