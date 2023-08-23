package com.space.quizapp.presentation.ui.points

import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizPointBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.points.adapter.QuizPointsListAdapter
import com.space.quizapp.utils.extensions.*
import kotlin.reflect.KClass

class QuizPointsFragment : QuizBaseFragment<QuizPointsViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz_point
    override val viewModelClass: KClass<QuizPointsViewModel>
        get() = QuizPointsViewModel::class

    private val binding by viewBinding(FragmentQuizPointBinding::bind)
    private val args: QuizPointsFragmentArgs by navArgs()
    override fun onBind() {
        setNavigation()
        setObserver()
    }

    private val adapter by lazy {
        QuizPointsListAdapter()
    }

    private fun setObserver(){
        setAdapter()
        viewModel.getQuiz(args.userId)
        observe(viewModel.quizData){
            if (it.isNotEmpty()) {
                binding.notEarnedTextView.visibility = View.GONE
                adapter.submitList(it.sortedBy{it.id})
            }else{
                binding.notEarnedTextView.visibility = View.VISIBLE
            }
        }
    }

    private fun setAdapter(){
        binding.scoreRecyclerView.adapter = adapter
    }

    private fun setNavigation() {
        with(binding){
            navBackImageButton.setOnClickListener {
                popBackStack(it)
            }
            logOutButton.setOnClickListener {
                showLogOutDialog()
            }
        }
    }

    private fun showLogOutDialog() {
        showDialog(R.layout.dialog_listener,getString(R.string.leaving_question), onPositiveButtonClick = {
            lifecycleScope {
                viewModel.clearUserSession()
            }
        })
    }
}