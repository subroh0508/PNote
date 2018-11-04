package net.subroh0508.pnote.auth.domain.usecase

import net.subroh0508.pnote.auth.infra.repository.AuthRepository

class UserSignOutUseCaseImpl(private val repository: AuthRepository) : UserSignOutUseCase {
    override fun execute() = repository.signOut()
}