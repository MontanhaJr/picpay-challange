package com.picpay.desafio.android.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.name.text = user.name
        binding.username.text = user.username
        binding.progressBar.visibility = android.view.View.VISIBLE

        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.picture, object : Callback {
                override fun onSuccess() {
                    binding.progressBar.visibility = android.view.View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressBar.visibility = android.view.View.GONE
                }
            })
    }
}
