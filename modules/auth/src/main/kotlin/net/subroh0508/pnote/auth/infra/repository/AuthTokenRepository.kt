package net.subroh0508.pnote.auth.infra.repository

import kotlinx.coroutines.Deferred
import net.subroh0508.pnote.auth.domain.entity.Token

interface AuthTokenRepository {
    fun existsToken(): Deferred<Token>
}