package com.ronlu.licensemaster.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ronlu.licensemaster.databinding.RecyclerviewCarItemBinding
import com.ronlu.licensemaster.databinding.RecyclerviewMotorcycleItemBinding
import com.ronlu.licensemaster.databinding.RecyclerviewPublicItemBinding
import com.ronlu.licensemaster.domain.model.Items

sealed class MasterViewHolder(binding: ViewBinding) :  RecyclerView.ViewHolder(binding.root){

    var itemClickListener : ((view: View, item: Items, position: Int) -> Unit)? = null

    class CarViewHolder(private val binding: RecyclerviewCarItemBinding) : MasterViewHolder(binding) {
        fun bind(car: Items.Car) {
            binding.recyclerViewLBLTitle.text = car.title
            binding.recyclerViewLBLColor.text = car.color
            binding.recyclerViewLBLFinishLevel.text = car.finishLevel
            binding.recyclerViewLBLIsPrivate.text = car.isPrivate
            binding.recyclerViewLBLManufacture.text = car.manufacture
            binding.recyclerViewLBLYear.text = car.year.toString()
            binding.recyclerViewLBLFuelType.text = car.fuelType
            binding.recyclerViewLBLPlateNumber.text = car.plateNumber.toString()
            binding.recyclerViewFABSave.setOnClickListener {view ->
                itemClickListener?.let { it(view, car, adapterPosition) }
            }
        }
    }

    class MotorcycleViewHolder(private val binding: RecyclerviewMotorcycleItemBinding) : MasterViewHolder(binding) {
        fun bind(motorcycle: Items.Motorcycle) {
            binding.recyclerViewLBLTitle.text = motorcycle.title
            binding.recyclerViewLBLColor.text = motorcycle.color
            binding.recyclerViewLBLFinishLevel.text = motorcycle.finishLevel
            binding.recyclerViewLBLIsPrivate.text = motorcycle.isPrivate
            binding.recyclerViewLBLManufacture.text = motorcycle.manufacture
            binding.recyclerViewLBLYear.text = motorcycle.year.toString()
            binding.recyclerViewLBLFuelType.text = motorcycle.fuelType
            binding.recyclerViewLBLPlateNumber.text = motorcycle.plateNumber.toString()
            binding.recyclerViewLBLRearTire.text = motorcycle.rearTire
            binding.recyclerViewLBLFrontTire.text = motorcycle.frontTire
            binding.recyclerViewFABSave.setOnClickListener { view ->
                itemClickListener?.let { it(view, motorcycle, adapterPosition) }
            }
        }
    }

    class PublicViewHolder(private val binding: RecyclerviewPublicItemBinding) : MasterViewHolder(binding){
        fun bind(publicVehicle : Items.PublicVehicle){
                binding.recyclerViewLBLPlateNumber.text = publicVehicle.plateNumber.toString()
                binding.recyclerViewLBLColor.text = publicVehicle.color
                binding.recyclerViewLBLManufacture.text = publicVehicle.manufacture
                binding.recyclerViewLBLEu.text = publicVehicle.euLevel
                binding.recyclerViewLBLNumberOfSeats.text = publicVehicle.numberOfSeats
                binding.recyclerViewLBLVehicleType.text = publicVehicle.vehicleType
                binding.recyclerViewLBLWeight.text = publicVehicle.weight.toString()
                binding.recyclerViewLBLYear.text = publicVehicle.year.toString()
                binding.recyclerViewFABSave.setOnClickListener { view ->
                    itemClickListener?.let { it(view, publicVehicle, adapterPosition) }
                }
        }
    }
}