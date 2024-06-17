package com.saulhervas.listausuariosapp.ui.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.saulhervas.listausuariosapp.data.model.User
import com.saulhervas.listausuariosapp.databinding.ItemUserBinding
import com.saulhervas.listausuariosapp.ui.fragments.MainFragmentDirections

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemUserBinding.bind(view)

    fun bind(user: User) {
        binding.tvID.text = user.id.toString()
        binding.tvName.text = user.name
        binding.tvDate.text = user.birthDate

        binding.rowLayout.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragment2ToUserDetail(user)
            it.findNavController().navigate(action)
        }
    }
}