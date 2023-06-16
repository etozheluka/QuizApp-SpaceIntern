package com.space.quizapp.presentation.ui.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T : Any, VB : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseViewHolder<VB>>(diffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding = createBinding(parent)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val item = getItem(position)
        onBind(holder.binding, item, onClickCallback)

    }

    private var onClickCallback: ((T) -> Unit)? = null

    fun onItemClickListener(onClickCallback: (T) -> Unit) {
        this.onClickCallback = onClickCallback
    }

    protected abstract fun createBinding(parent: ViewGroup): VB
    protected abstract fun onBind(binding: VB, item: T,onClickCallback: ((T) -> Unit)?)

}

class BaseViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)