package com.ronlu.licensemaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ronlu.licensemaster.data.local.MasterDatabase
import com.ronlu.licensemaster.data.repository.MasterRepository
import com.ronlu.licensemaster.presentation.FavoriteFragment
import com.ronlu.licensemaster.presentation.SearchFragment
import com.ronlu.licensemaster.presentation.viewModels.MasterViewModel
import com.ronlu.licensemaster.presentation.viewModels.MasterViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MasterViewModel
    lateinit var repository: MasterRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = MasterRepository(MasterDatabase.invoke(this))
        val viewModelFactory = MasterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MasterViewModel::class.java)

        val main_BTN_search = findViewById<AppCompatImageButton>(R.id.main_BTN_search)
        val main_BTN_favorite = findViewById<AppCompatImageButton>(R.id.main_BTN_favorite)

        val searchFragment = SearchFragment()
        val favoriteFragment = FavoriteFragment()
        showFragment(searchFragment)

        main_BTN_search.setOnClickListener { showFragment(searchFragment) }
        main_BTN_favorite.setOnClickListener { showFragment(favoriteFragment) }

    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_fragmentContainer, fragment).commit()
    }
}