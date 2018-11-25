package net.subroh0508.pnote.auth.domain.usecase

import net.subroh0508.pnote.auth.domain.entity.User

interface GetCurrentUserUseCase {
    fun execute(): User?
}