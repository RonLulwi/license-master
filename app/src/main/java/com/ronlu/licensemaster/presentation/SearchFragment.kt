package com.ronlu.licensemaster.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.ronlu.licensemaster.MainActivity
import com.ronlu.licensemaster.R
import com.ronlu.licensemaster.presentation.adapters.MasterAdapter
import com.ronlu.licensemaster.presentation.viewModels.MasterViewModel
import com.ronlu.licensemaster.utils.Resource

class SearchFragment : Fragment(R.layout.fragment_search) {
    lateinit var viewModel: MasterViewModel
    lateinit var masterAdapter: MasterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerview(view)

        viewModel.items.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> response.data?.let {
                    masterAdapter.differ.submitList(it)
                }
                is Resource.Error -> Snackbar.make(view, "Error...", Snackbar.LENGTH_SHORT).show()
                is Resource.Loading -> Snackbar.make(view, "Loading...", Snackbar.LENGTH_SHORT).show()
            }
        })


        masterAdapter.itemClickListener = {v, item, _ ->
            Snackbar.make(v, "Item has been saved", Snackbar.LENGTH_SHORT).show()
            viewModel.saveItem(item)
        }

        val fragmentSearch_BTN_search = view.findViewById<MaterialButton>(R.id.fragmentSearch_BTN_search)
        val search_RBT_motorcycle = view.findViewById<AppCompatRadioButton>(R.id.search_RBT_motorcycle)
        val search_RBT_car = view.findViewById<AppCompatRadioButton>(R.id.search_RBT_car)
        val search_RBT_public = view.findViewById<AppCompatRadioButton>(R.id.search_RBT_public)
        val fragmentSearch_EDT_search = view.findViewById<AppCompatEditText>(R.id.fragmentSearch_EDT_search)




        fragmentSearch_BTN_search.setOnClickListener {
            val userInput = fragmentSearch_EDT_search.text.toString()
            if(userInput.isBlank() || userInput.isEmpty())
                Snackbar.make(view, "Enter plate", Snackbar.LENGTH_SHORT).show()
            else
                when{
                    search_RBT_motorcycle.isChecked -> viewModel.getMotorcycleData(userInput)
                    search_RBT_car.isChecked -> { viewModel.getCarData(userInput) }
                    search_RBT_public.isChecked -> {viewModel.getPublicData(userInput)}
                    else -> Toast.makeText(view.context, "Choose an vehicle type", Toast.LENGTH_SHORT).show()
                }
        }


    }

    private fun setupRecyclerview(view: View) {
        masterAdapter = MasterAdapter()
        val rv: RecyclerView = view.findViewById(R.id.recyclerView_search)
        rv.apply {
            adapter = masterAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}