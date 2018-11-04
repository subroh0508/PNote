package net.subroh0508.pnote.auth.domain.entity

import net.subroh0508.pnote.common.ddd.Entity
import net.subroh0508.pnote.common.ddd.Identifier

data class Token(
    override val id: Token.Id
) : Entity<Token.Id>(id) {
    constructor(id: String) : this(Id(id))

    class Id(value: String) : Identifier<String>(value)
}