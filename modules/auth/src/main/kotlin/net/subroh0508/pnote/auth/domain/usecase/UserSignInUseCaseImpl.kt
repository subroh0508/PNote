package net.subroh0508.pnote.auth.domain.usecase

import net.subroh0508.pnote.auth.domain.entity.User
import net.subroh0508.pnote.auth.infra.repository.AuthRepository

class UserSignInUseCaseImpl(private val repository: AuthRepository) : UserSignInUseCase {
    override suspend fun execute(email: String, password: String): User {
        val currentUser = repository.getCurrentUser()

        if (currentUser != null) {
            return currentUser
        }

        return repository.signInUser(email, password)
    }
}