package com.saulhervas.listausuariosapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saulhervas.listausuariosapp.R
import com.saulhervas.listausuariosapp.data.model.User


class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var users = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user = user)

    }

    override fun getItemCount() = users.size

    fun setDataset(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }
    fun updateDataset(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    fun clearDataset() {
        this.users = emptyList()
        notifyDataSetChanged()
    }

    fun getUserAt(position: Int): User {
        return users[position]
    }


}
