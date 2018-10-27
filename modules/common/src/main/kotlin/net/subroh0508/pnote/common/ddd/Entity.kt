package net.subroh0508.pnote.common.ddd

abstract class Entity<out T: Identifier<*>>(open val id: T) {
    override fun equals(other: Any?) = other is Entity<*> && other.id == id

    override fun hashCode() = id.hashCode()
}
