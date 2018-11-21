package net.subroh0508.pnote.common.presenter

import net.subroh0508.pnote.common.mvp.ValidateVersionContract

abstract class ValidateVersionPresenter(
    private val validateVersionView: ValidateVersionContract.View
) : ValidateVersionContract.Presenter {
    override fun startNotifyUpdateActivity() = validateVersionView.startNotifyUpdateActivity()
}