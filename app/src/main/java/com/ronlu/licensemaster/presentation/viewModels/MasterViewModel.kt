package com.ronlu.licensemaster.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronlu.licensemaster.data.CarResponse
import com.ronlu.licensemaster.data.VehicleDto
import com.ronlu.licensemaster.data.mapper.toCar
import com.ronlu.licensemaster.data.mapper.toMotorcycle
import com.ronlu.licensemaster.data.mapper.toPublic
import com.ronlu.licensemaster.data.repository.MasterRepository
import com.ronlu.licensemaster.domain.model.Items
import com.ronlu.licensemaster.utils.Constants
import com.ronlu.licensemaster.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MasterViewModel(
    private val repository: MasterRepository
) : ViewModel(){

    private val carItems = repository.getCarItems()
    private val motorcycleItems = repository.getMotorcycleItems()
    private val publicItems = repository.getPublicItems()
    private val mergedItems  = MediatorLiveData<List<Items>>()

    private val _items = MutableLiveData<Resource<List<Items>>>()
    val items: LiveData<Resource<List<Items>>>
        get() = _items

    init {
        mergedItems.addSource(carItems) {mergedItems.value = combineData()}
        mergedItems.addSource(motorcycleItems) {mergedItems.value = combineData()}
        mergedItems.addSource(publicItems) {mergedItems.value = combineData()}
    }

    private fun combineData(): List<Items> {
        val cars = carItems.value.orEmpty()
        val motorcycles = motorcycleItems.value.orEmpty()
        val public = publicItems.value.orEmpty()

        val combinedList = mutableListOf<Items>()
        combinedList.addAll(cars)
        combinedList.addAll(motorcycles)
        combinedList.addAll(public)
        return combinedList
    }

    // Retrofit Calls
    fun getCarData(plateNumber: String) = viewModelScope.launch {
        _items.postValue(Resource.Loading())
        //val filter = "{\"mispar_rechev\": \"$plateNumber\"}";
        val response = repository.getCarData(Constants.getFilter(plateNumber))
        _items.postValue(handleCarResponse(response))

    }

    private fun handleCarResponse(response: Response<CarResponse<VehicleDto.CarD>>): Resource<List<Items>> {
        if(response.isSuccessful){
            response.body()?.let { result ->
                var carsList: MutableList<Items> = mutableListOf()
                for(car in result.result.records){
                    carsList.add(car.toCar())
                }
                return Resource.Success(carsList)
            }
        }
        return Resource.Error(response.message())
    }

    fun getMotorcycleData(plateNumber: String) = viewModelScope.launch {
        _items.postValue(Resource.Loading())
        //val filter = "{\"mispar_rechev\": \"$plateNumber\"}";
        val response = handleMotorcycleResponse(repository.getMotorcycleData(Constants.getFilter(plateNumber)))
        _items.postValue(response)
    }

    private fun handleMotorcycleResponse(response: Response<CarResponse<VehicleDto.MotorcycleD>>): Resource<List<Items>> {
        if (response.isSuccessful){
            response.body()?.let { result ->
                var motorcycleList: MutableList<Items> = mutableListOf()
                for(motorcycle in result.result.records){
                    motorcycleList.add(motorcycle.toMotorcycle())
                }
                return Resource.Success(motorcycleList)
            }
        }
        return Resource.Error(response.message())
    }

    fun getPublicData(plateNumber: String) = viewModelScope.launch {
        _items.postValue(Resource.Loading())
        val response = repository.getPublicData(Constants.getFilter(plateNumber))
        _items.postValue(handlePublicResponse(response))
    }

    private fun handlePublicResponse(response: Response<CarResponse<VehicleDto.PublicD>>): Resource<List<Items>> {
        if (response.isSuccessful){
            response.body()?.let { result ->
                var publicList = mutableListOf<Items.PublicVehicle>()
                for (public in result.result.records)
                    publicList.add(public.toPublic())
            return Resource.Success(publicList)
            }
        }
        return Resource.Error(response.message())
    }


    // Room Calls
    fun deleteItem(item: Items) = viewModelScope.launch { repository.deleteItem(item) }

    fun saveItem(item: Items){
        when(item){
            is Items.Car -> viewModelScope.launch { repository.upsertCar(item) }
            is Items.Motorcycle -> viewModelScope.launch { repository.upsertMotorcycle(item) }
            is Items.PublicVehicle -> viewModelScope.launch { repository.upsertPublic(item) }
        }
    }

    fun getSavedItems() : LiveData<List<Items>> {
        return mergedItems
    }


}