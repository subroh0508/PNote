package net.subroh0508.pnote.common.ddd

import java.io.Serializable

abstract class Identifier<T>(val value: T) : Serializable {
    override fun equals(other: Any?)
            = this === other || (other is Identifier<*> && value == other.value)

    override fun hashCode(): Int = value?.hashCode() ?: 0
}
