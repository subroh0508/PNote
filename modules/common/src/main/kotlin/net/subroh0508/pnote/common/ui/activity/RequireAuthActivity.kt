package net.subroh0508.pnote.common.ui.activity

import android.content.Intent
import net.subroh0508.pnote.common.mvp.RequireAuthContract

abstract class RequireAuthActivity : ValidateVersionActivity(), RequireAuthContract.View {
    lateinit var intentForRequireAuthActivity: Intent

    override fun startWelcomeActivity() {
        startActivity(intentForRequireAuthActivity)
    }
}