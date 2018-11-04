package net.subroh0508.pnote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.subroh0508.pnote.auth.presentation.ui.AuthActivity
import net.subroh0508.pnote.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()

        startActivity(Intent(this, AuthActivity::class.java))
    }

}
