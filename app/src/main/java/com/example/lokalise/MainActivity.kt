package com.example.lokalise

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.lokalise.databinding.ActivityMainBinding
import com.lokalise.sdk.Lokalise
import com.lokalise.sdk.LokaliseContextWrapper
import com.lokalise.sdk.LokaliseFallbackStrategy

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // It is important init right after the "super.onCreate()"
        Log.i("start","SDK init test here");
        Lokalise.init(this, "64681447624e3730df9c00.98683013", "f9e18b0260ebe9916613b1cb063e04a7c1ed", LokaliseFallbackStrategy.DEFAULT);

        // Add this only if you want to use pre-release bundles
//        Lokalise.setPreRelease(true);

        // Fetch the latest translations from Lokalise (can be called anywhere)
        Lokalise.updateTranslations();

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
//        super.attachBaseContext(newBase?.let { LokaliseContextWrapper.wrap(it) });
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}