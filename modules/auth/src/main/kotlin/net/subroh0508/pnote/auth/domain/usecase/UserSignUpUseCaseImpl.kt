package net.subroh0508.pnote.auth.domain.usecase

import net.subroh0508.pnote.auth.domain.entity.User
import net.subroh0508.pnote.auth.infra.repository.AuthRepository

class UserSignUpUseCaseImpl(private val repository: AuthRepository) : UserSignUpUseCase {
    override suspend fun execute(email: String, password: String): User {
        val currentUser = repository.getCurrentUser()

        if (currentUser != null) {
            return currentUser
        }

        return repository.signUpUser(email, password)
    }
}