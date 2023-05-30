package com.space.quizapp.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffCallback<M : Any> : DiffUtil.ItemCallback<M>() {

    override fun areItemsTheSame(oldItem: M, newItem: M): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: M, newItem: M): Boolean {
        return oldItem == newItem
    }
}