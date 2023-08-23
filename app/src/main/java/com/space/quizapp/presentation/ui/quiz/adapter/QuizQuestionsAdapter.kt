package com.space.quizapp.presentation.ui.quiz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.space.quizapp.R
import com.space.quizapp.databinding.ItemAnswerBinding
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.presentation.ui.base.adapter.BaseListAdapter
import com.space.quizapp.presentation.ui.base.adapter.BaseViewHolder
import com.space.quizapp.utils.extensions.setTextColorCompat
import com.space.quizapp.utils.extensions.setTint

class QuizQuestionsAdapter(private val onAnswerSelected: (Boolean, Int, Boolean) -> Unit) :
    BaseListAdapter<QuizQuestionUIModel.Answer, ItemAnswerBinding>(MyItemDiffCallback()) {

    var clickable = true
    private var recyclerView: RecyclerView? = null

    private fun getItemBinding(position: Int): ItemAnswerBinding? {
        val viewHolder = recyclerView!!.findViewHolderForAdapterPosition(position)
        return (viewHolder as? BaseViewHolder<ItemAnswerBinding>)?.binding
    }

    override fun createBinding(parent: ViewGroup): ItemAnswerBinding {
        return ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBind(
        binding: ItemAnswerBinding,
        item: QuizQuestionUIModel.Answer,
        position: Int
    ) {
        with(binding) {
            optionTitleTextView.text = item.answer
            answerCardView.setTint(R.color.neutral_light_gray)
            correctPointTextView.visibility = View.GONE
            optionTitleTextView.setTextColorCompat(R.color.neutral_dark_grey)
            answerCardView.setOnClickListener {
                if (clickable) {
                    handleAnswerClick(binding, item, position)
                }
            }
        }
    }

    private fun handleAnswerClick(
        binding: ItemAnswerBinding,
        item: QuizQuestionUIModel.Answer,
        position: Int
    ) {
        val correctAnswer = item.correctAnswer
        val plusPoint = item.point
        val isLastQuestion = item.lastQuestion
        val isCorrectAnswer = correctAnswer == position

        binding.root.setTint(if (isCorrectAnswer) R.color.success_green else R.color.wrong_red)
        binding.optionTitleTextView.setTextColorCompat(R.color.neutral_white)
        clickable = false

        if (isCorrectAnswer) {
            handleAnswer(binding, true, plusPoint, isLastQuestion)
        } else {
            val correctBinding = getItemBinding(correctAnswer)
            correctBinding?.root?.setTint(R.color.success_green)
            correctBinding?.optionTitleTextView?.setTextColorCompat(R.color.neutral_white)
            handleAnswer(binding, false, isLastQuestion = isLastQuestion)
        }
    }

    private fun handleAnswer(
        binding: ItemAnswerBinding,
        isCorrectAnswer: Boolean,
        plusPoint: Int = 0,
        isLastQuestion: Boolean
    ) {
        onAnswerSelected.invoke(isCorrectAnswer, plusPoint, isLastQuestion)
        if (isCorrectAnswer) {
            binding.correctPointTextView.apply {
                visibility = View.VISIBLE
                text = "+$plusPoint"
            }
        }

    }

    private class MyItemDiffCallback : DiffUtil.ItemCallback<QuizQuestionUIModel.Answer>() {
        override fun areItemsTheSame(
            oldItem: QuizQuestionUIModel.Answer,
            newItem: QuizQuestionUIModel.Answer
        ): Boolean {
            return oldItem.answer == newItem.answer
        }

        override fun areContentsTheSame(
            oldItem: QuizQuestionUIModel.Answer,
            newItem: QuizQuestionUIModel.Answer
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }
}