package net.subroh0508.pnote.common.presenter

import net.subroh0508.pnote.common.mvp.RequireAuthContract

abstract class RequireAuthPresenter(
    private val requireAuthView: RequireAuthContract.View
) : RequireAuthContract.Presenter, ValidateVersionPresenter(requireAuthView) {
    override fun startWelcomeActivity() = requireAuthView.startWelcomeActivity()
}