package com.codingwithjks.weatherapp.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import android.os.Build
import androidx.annotation.RequiresApi
import com.codingwithjks.weatherapp.MainActivity
import com.codingwithjks.weatherapp.base.BaseViewHolder
import com.codingwithjks.weatherapp.base.ObservableRecyclerViewAdapter
import com.codingwithjks.weatherapp.databinding.ItemCityBinding


class CityAdapter ( var cardList: ObservableArrayList<String>) :
    ObservableRecyclerViewAdapter<String, BaseViewHolder<String>>(cardList){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        return Holder(ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<String>, position: Int) {
        holder.bind(cardList[position])
    }

    inner class Holder(private val binding: ItemCityBinding) : BaseViewHolder<String>(binding.root) {
        var name:String?=null
        @SuppressLint("ResourceType")
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun bind(item: String) {
            binding.holder=this
            binding.tvCity.text=cardList[position]
        }

        fun onCardClick(){

            val intent=Intent(binding.root.context,MainActivity::class.java)
            binding.root.context.startActivity(intent)
        }
    }
}