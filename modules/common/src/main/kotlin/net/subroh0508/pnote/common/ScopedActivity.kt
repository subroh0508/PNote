package net.subroh0508.pnote.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class ScopedActivity : AppCompatActivity(), CoroutineScope {
    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Dispatchers
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()

        job.cancel()
    }
}