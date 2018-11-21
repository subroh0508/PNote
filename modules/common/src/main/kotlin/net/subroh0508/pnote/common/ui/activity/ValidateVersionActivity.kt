package net.subroh0508.pnote.common.ui.activity

import android.content.Intent
import net.subroh0508.pnote.common.mvp.ValidateVersionContract

abstract class ValidateVersionActivity : ScopedActivity(), ValidateVersionContract.View {
    lateinit var intentForNotifyUpdateActivity: Intent

    override fun startNotifyUpdateActivity() {
        startActivity(intentForNotifyUpdateActivity)
    }
}