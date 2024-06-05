package com.saulhervas.listausuariosapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saulhervas.listausuariosapp.UserDiffCallback
import com.saulhervas.listausuariosapp.data.model.User
import com.saulhervas.listausuariosapp.databinding.ItemUserBinding


class UserAdapter(
    private val onClick: (Int) -> Unit
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    fun submitList(newUsers: List<User>) {
        val diffCallback = UserDiffCallback(users, newUsers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        users = newUsers
        diffResult.dispatchUpdatesTo(this)
    }

    inner class UserViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var onClick: (User) -> Unit

        constructor(binding: ItemUserBinding, onClick: (User) -> Unit) : this(binding) {
            this.onClick = onClick
        }

        fun bind(user: User) {
            binding.tvTextName.text = user.name
            binding.root.setOnClickListener { onClick(user) }
        }
    }
}