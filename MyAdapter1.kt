package com.example.lifeshare

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter1(private val userList: ArrayList<User1>) :
    RecyclerView.Adapter<MyAdapter1.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val FullName: TextView = itemView.findViewById(R.id.fullnme)
        val Amount: TextView = itemView.findViewById(R.id.amont)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item1, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user1: User1 = userList[position]
        holder.FullName.text = user1.FullName
        holder.Amount.text = user1.Amount
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
