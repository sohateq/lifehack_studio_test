package com.akameko.testforlifehackstudio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.akameko.testforlifehackstudio.ui.main.DetailFragment
import com.akameko.testforlifehackstudio.ui.main.MainFragment

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

    fun showDetailsFragment() {
        val detailFragment = DetailFragment()
        val fm = supportFragmentManager
        fm.beginTransaction()
                .replace(R.id.container, detailFragment)
                .addToBackStack("")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}