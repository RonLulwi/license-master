package com.ronlu.licensemaster.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ronlu.licensemaster.MainActivity
import com.ronlu.licensemaster.R
import com.ronlu.licensemaster.presentation.adapters.MasterAdapter
import com.ronlu.licensemaster.presentation.viewModels.MasterViewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    lateinit var viewModel: MasterViewModel
    lateinit var masterAdapter: MasterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerview(view)

        viewModel.getSavedItems().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty())
                Snackbar.make(view, "no fav Items", Snackbar.LENGTH_SHORT).show()
            masterAdapter.differ.submitList(it)
        })

        masterAdapter.itemClickListener = {view, item, position ->
            viewModel.deleteItem(item)
            Snackbar.make(view, "Car has ben Deleted!", Snackbar.LENGTH_SHORT).show()
        }

    }


    private fun setupRecyclerview(view: View) {
        masterAdapter = MasterAdapter()
        val rv : RecyclerView = view.findViewById(R.id.recyclerView_favorite)
        rv.apply {
            adapter = masterAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}