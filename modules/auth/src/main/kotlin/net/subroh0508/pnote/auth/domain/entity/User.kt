package net.subroh0508.pnote.auth.domain.entity

import net.subroh0508.pnote.common.ddd.Entity
import net.subroh0508.pnote.common.ddd.Identifier

data class User(
    override val id: User.Id,
    val email: String
) : Entity<User.Id>(id) {
    constructor(id: String, email: String) : this(Id(id), email)

    class Id(value: String) : Identifier<String>(value)
}