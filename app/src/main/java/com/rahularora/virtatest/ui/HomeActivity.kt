package com.rahularora.virtatest.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahularora.virtatest.R
import com.rahularora.virtatest.api.methods.appModule
import com.rahularora.virtatest.databinding.ActivityHomeBinding
import com.rahularora.virtatest.utils.SessionManager
import com.rahularora.virtatest.viewmodel.MainRecyclerViewAdapter
import com.rahularora.virtatest.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: MainViewModel by inject()
    private val recyclerViewAdapter = MainRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        startKoin()
        binding.toolbar.title = getString(R.string.title_stations)
        setSupportActionBar(binding.toolbar)

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.adapter = recyclerViewAdapter
        }
        observeFromViewModel()
        viewModel.fetchStations(this)
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@HomeActivity)
            modules(appModule)
        }
    }

    private fun observeFromViewModel() {
        viewModel.stations1.observe(this) { stations ->
            recyclerViewAdapter.loadData(stations)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_logout -> logOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logOut() {
        SessionManager.clearData(this)
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}