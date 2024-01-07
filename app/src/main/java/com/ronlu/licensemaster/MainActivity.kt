package com.ronlu.licensemaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.ronlu.licensemaster.data.local.MasterDatabase
import com.ronlu.licensemaster.data.repository.MasterRepository
import com.ronlu.licensemaster.presentation.FavoriteFragment
import com.ronlu.licensemaster.presentation.SearchFragment
import com.ronlu.licensemaster.presentation.viewModels.MasterViewModel
import com.ronlu.licensemaster.presentation.viewModels.MasterViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MasterViewModel
    lateinit var repository: MasterRepository
    lateinit var main_LBL_title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = MasterRepository(MasterDatabase.invoke(this))
        val viewModelFactory = MasterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MasterViewModel::class.java)

        val main_BTN_search = findViewById<ExtendedFloatingActionButton>(R.id.main_BTN_search)
        val main_BTN_favorite = findViewById<ExtendedFloatingActionButton>(R.id.main_BTN_favorite)
        main_LBL_title = findViewById(R.id.main_LBL_title)

        val searchFragment = SearchFragment()
        val favoriteFragment = FavoriteFragment()
        showFragment(searchFragment, "License Master")

        main_BTN_search.setOnClickListener { showFragment(searchFragment, "License Master") }
        main_BTN_favorite.setOnClickListener { showFragment(favoriteFragment, "Favorites") }

    }

    private fun showFragment(fragment: Fragment, title: String) {
        main_LBL_title.text = title
        supportFragmentManager.beginTransaction().replace(R.id.main_fragmentContainer, fragment).commit()
    }
}