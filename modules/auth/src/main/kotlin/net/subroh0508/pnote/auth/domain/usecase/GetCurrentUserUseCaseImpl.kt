package net.subroh0508.pnote.auth.domain.usecase

import net.subroh0508.pnote.auth.infra.repository.AuthRepository

class GetCurrentUserUseCaseImpl(private val repository: AuthRepository) : GetCurrentUserUseCase {
    override fun execute() = repository.getCurrentUser()
}