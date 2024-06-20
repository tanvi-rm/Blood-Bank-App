package com.example.lifeshare

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class MyAdapter(private val userList: ArrayList<User>,activity:Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    val activity = activity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return MyViewHolder(itemView, activity)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user : User =userList[position]
        holder.First_Name.text = user.First_Name
        holder.Last_Name.text = user.Last_Name
        holder.units.text = user.units
        holder.date.text = user.date
        holder.bldgrp.text=user.bldgrp
        holder.location1.text= user.location1
        holder.Phone_Number.text= user.Phone_Number

        holder.parentcard.setOnClickListener {
            val intent = Intent(activity, Accept_form::class.java)
            activity.startActivity(intent)
            Toast.makeText(activity,user.First_Name,Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    public class MyViewHolder(itemView: View,activity: Context) : RecyclerView.ViewHolder(itemView) {

        val parentcard = itemView.findViewById<CardView>(R.id.card)
        val First_Name : TextView = itemView.findViewById(R.id.fstName)
        val Last_Name : TextView = itemView.findViewById(R.id.lstName)
        val units : TextView = itemView.findViewById(R.id.units)
        val date : TextView = itemView.findViewById(R.id.date)
        val bldgrp:TextView=itemView.findViewById(R.id.bldgrp)
        val location1: TextView=itemView.findViewById(R.id.location1)
        val Phone_Number: TextView = itemView.findViewById(R.id.phone)

    }
}