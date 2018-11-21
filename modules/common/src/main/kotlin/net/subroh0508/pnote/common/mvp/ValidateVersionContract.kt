package net.subroh0508.pnote.common.mvp

interface ValidateVersionContract {
    interface View : BaseContract.View {
        fun startNotifyUpdateActivity()
    }

    interface Presenter : BaseContract.Presenter {
        fun startNotifyUpdateActivity()
    }
}