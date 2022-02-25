package com.ifsp.pedrapapeltesourafragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.ifsp.pedrapapeltesourafragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        with(supportFragmentManager.beginTransaction()) {
            setReorderingAllowed(true)
            addToBackStack("principal")
            add(R.id.frag, MainFragment(), "MainFragment")
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == R.id.configFragment) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack("configuracoes")
                replace(R.id.frag, ConfigFragment(), "ConfigFragment")
            }
            true
        } else
            false
}
