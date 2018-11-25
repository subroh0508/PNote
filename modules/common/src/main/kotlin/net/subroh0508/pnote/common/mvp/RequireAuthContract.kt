package net.subroh0508.pnote.common.mvp

interface RequireAuthContract {
    interface View : ValidateVersionContract.View {
        fun startWelcomeActivity()
    }
}