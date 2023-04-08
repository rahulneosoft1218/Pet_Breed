package com.android.petsdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.petsdetails.R
import com.android.petsdetails.databinding.ListChildBinding

class ChildAdapter(private var mList: MutableList<String>) :
    RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    lateinit var binding: ListChildBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListChildBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val petName: String = mList.get(position)
        holder.bind(petName)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(private val binding: ListChildBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(petName: String) {
            binding.ivIcon.setImageResource(R.drawable.ic_pets)
            binding.tvTitle.text = petName
        }
    }
}