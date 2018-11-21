package net.subroh0508.pnote.common.ui.fragment

import android.content.Intent
import net.subroh0508.pnote.common.mvp.RequireAuthContract

abstract class RequireAuthFragment : ValidateVersionFragment(), RequireAuthContract.View {
    lateinit var intentForRequireAuthActivity: Intent

    override fun startWelcomeActivity() {
        startActivity(intentForRequireAuthActivity)
    }
}