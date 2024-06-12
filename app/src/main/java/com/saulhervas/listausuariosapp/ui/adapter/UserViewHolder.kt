package com.saulhervas.listausuariosapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.saulhervas.listausuariosapp.data.model.User
import com.saulhervas.listausuariosapp.databinding.ItemUserBinding

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemUserBinding.bind(view)

    fun bind(user: User) {
        binding.tvID.text = user.id.toString()
        binding.tvName.text = user.name
        binding.tvDate.text = user.birthDate


    }
}