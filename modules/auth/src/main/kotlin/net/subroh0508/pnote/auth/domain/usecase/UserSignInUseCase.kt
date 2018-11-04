package net.subroh0508.pnote.auth.domain.usecase

import net.subroh0508.pnote.auth.domain.entity.User

interface UserSignInUseCase {
    suspend fun execute(email: String, password: String): User
}