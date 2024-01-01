package com.ronlu.licensemaster.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ronlu.licensemaster.data.repository.MasterRepository

class MasterViewModelFactory(
    private val masterRepository: MasterRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MasterViewModel(masterRepository) as T
    }
}