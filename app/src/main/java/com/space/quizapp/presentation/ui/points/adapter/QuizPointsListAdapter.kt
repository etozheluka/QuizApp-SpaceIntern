package com.space.quizapp.presentation.ui.points.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.space.quizapp.databinding.ItemSubjectBinding
import com.space.quizapp.presentation.model.QuizPointsUIModel
import com.space.quizapp.presentation.ui.base.adapter.BaseListAdapter
import com.space.quizapp.utils.extensions.viewBinding

class QuizPointsListAdapter: BaseListAdapter<QuizPointsUIModel, ItemSubjectBinding>(MyItemDiffCallback()) {

    override fun createBinding(parent: ViewGroup): ItemSubjectBinding {
        return parent.viewBinding(ItemSubjectBinding::inflate)
    }


    override fun onBind(
        binding: ItemSubjectBinding,
        item: QuizPointsUIModel,
        position: Int
    ) {
        with(binding){
            subjectTextView.text = item.quizTitle
            descriptionTextView.text = item.quizDescription
            iconSubjectImageView.load(item.image)
            startImageButton.text = item.quizPoints.toString()
        }
    }

    private class MyItemDiffCallback : DiffUtil.ItemCallback<QuizPointsUIModel>() {

        override fun areItemsTheSame(oldItem: QuizPointsUIModel, newItem: QuizPointsUIModel): Boolean {
            // Compare based on unique identifiers of the items
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuizPointsUIModel, newItem: QuizPointsUIModel): Boolean {
            // Compare based on the actual content of the items
            return oldItem == newItem
        }
    }
}