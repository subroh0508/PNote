package net.subroh0508.pnote.common.ui.fragment

import android.content.Intent
import net.subroh0508.pnote.common.mvp.ValidateVersionContract

abstract class ValidateVersionFragment : ScopedFragment(), ValidateVersionContract.View {
    lateinit var intentForNotifyUpdateActivity: Intent

    override fun startNotifyUpdateActivity() {
        startActivity(intentForNotifyUpdateActivity)
    }
}