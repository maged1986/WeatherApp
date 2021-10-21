package com.magednan.cobbletaskapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.magednan.cobbletaskapplication.R
import com.magednan.cobbletaskapplication.databinding.CobbleListItemBinding
import com.magednan.cobbletaskapplication.model.Daily
import com.magednan.cobbletaskapplication.util.Constants.IMG_EX
import com.magednan.cobbletaskapplication.util.Constants.IMG_Url
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherAdapter @Inject constructor(val context: Context, val list: List<Daily>): RecyclerView.Adapter<WeatherAdapter.CobbleViewHolder>(){
    var lastItem=-1

    override fun onBindViewHolder(holder: CobbleViewHolder, position: Int) {
        if (holder.adapterPosition>lastItem){
            val animation= AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down)
            holder.binding.layoutParent.startAnimation(animation)
            lastItem=holder.adapterPosition
            var item = list.get(position)
            if (item != null) {
                holder.bind(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CobbleViewHolder {
        val binding= CobbleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CobbleViewHolder(binding)
    }


    inner class CobbleViewHolder(val binding: CobbleListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(daily: Daily) {
            binding.apply {
                //To get Img url
                var imgUrl= IMG_Url+daily.weather?.get(0)?.icon+ IMG_EX
                Glide.with(binding.root).load(imgUrl).centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemIvImage)
                //to convert date & time format
                val transformedDate = SimpleDateFormat("dd MMM yy",Locale.US)
                val transformedTime = SimpleDateFormat("HH:mm")
                //getting date String
                var date=transformedDate.format(Date(daily.dt!!.toLong() * 1000))
                val c = Calendar.getInstance()
                c.setTime(Date(daily.dt!!.toLong() * 1000))
                //getting day String
                val dayWeekText = SimpleDateFormat("EEEE").format(Date(daily.dt!!.toLong() * 1000))
                itemTvDate.text="Date"+"\n"+dayWeekText +"\n"+date
                //getting temp
                var temp= "Temp"+"\n"+"\n" +daily.temp?.day!!.toInt().toString()
                itemTvTemp.text=temp
                //getting sunrise time
                var sunrise=transformedTime.format(Date(daily.sunrise!!.toLong() * 1000))
                itemTvSunrise.text="sunrise"+"\n"+"\n"+sunrise
                //getting sunrise time
                var sunset=transformedTime.format(Date(daily.sunset!!.toLong() * 1000))
                itemTvSunset.text="sunset"+"\n"+"\n"+sunset
                itemTvDesc.text="description"+"\n"+"\n"+ daily.weather?.get(0)?.description
                itemTvWind.text="Wind Speed"+"\n"+"\n" + daily.wind_speed.toString()
               layoutParent.setOnClickListener {
                    binding.itemLlDetails.visibility=View.VISIBLE
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size ?:0
    }

}