package com.ronlu.licensemaster.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ronlu.licensemaster.R
import com.ronlu.licensemaster.databinding.RecyclerviewCarItemBinding
import com.ronlu.licensemaster.databinding.RecyclerviewMotorcycleItemBinding
import com.ronlu.licensemaster.databinding.RecyclerviewPublicItemBinding
import com.ronlu.licensemaster.domain.model.Items

class MasterAdapter : RecyclerView.Adapter<MasterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterViewHolder {
        return when(viewType){
            R.layout.recyclerview_car_item -> MasterViewHolder.CarViewHolder(
                RecyclerviewCarItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.recyclerview_motorcycle_item -> MasterViewHolder.MotorcycleViewHolder(
                RecyclerviewMotorcycleItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.recyclerview_public_item -> MasterViewHolder.PublicViewHolder(
                RecyclerviewPublicItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: MasterViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        when(holder){
            is MasterViewHolder.CarViewHolder -> holder.bind(differ.currentList[position] as Items.Car)
            is MasterViewHolder.MotorcycleViewHolder -> holder.bind(differ.currentList[position] as Items.Motorcycle)
            is MasterViewHolder.PublicViewHolder -> holder.bind(differ.currentList[position] as Items.PublicVehicle)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(differ.currentList[position]){
            is Items.Car -> R.layout.recyclerview_car_item
            is Items.Motorcycle -> R.layout.recyclerview_motorcycle_item
            is Items.PublicVehicle -> R.layout.recyclerview_public_item
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Items>(){
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return when{
                oldItem is Items.Car && newItem is Items.Car -> newItem.plateNumber == oldItem.plateNumber
                oldItem is Items.Motorcycle && newItem is Items.Motorcycle -> newItem.plateNumber == oldItem.plateNumber
                oldItem is Items.PublicVehicle && newItem is Items.PublicVehicle -> newItem.plateNumber == oldItem.plateNumber
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    var itemClickListener : ((view: View, item: Items, position: Int) -> Unit)? = null

}