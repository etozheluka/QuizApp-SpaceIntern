package com.space.quizapp.presentation.ui.home

import android.util.Log
import androidx.core.content.ContextCompat
import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizHomeBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.home.adapter.QuizListAdapter
import com.space.quizapp.utils.extensions.*
import kotlin.reflect.KClass

class QuizHomeFragment : QuizBaseFragment<QuizHomeViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz_home
    override val viewModelClass: KClass<QuizHomeViewModel>
        get() = QuizHomeViewModel::class

    private var currentUser: String? = null

    private val binding by viewBinding(FragmentQuizHomeBinding::bind)

    private val adapter by lazy {
        QuizListAdapter {
            viewModel.setNavigation(
                QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizFragment(
                    it.quizTitle,
                    it.questionsCount,
                    it.id,
                    )
            )
        }
    }

    override fun onBind() {
        setNavigation()
        showGpaScore()
        setObserver()
    }

    private fun showGpaScore() {
        viewModel.updateSession()
        /**
         * Observe the session and load the user points
         */
        observe(viewModel.session) { session ->
            session?.let {
                binding.helloUserTextView.text = buildString {
                    append(getString(R.string.hello_user))
                    append(it)
                }
                viewModel.loadUserPoints(it)
                currentUser = it
            }
        }

        /**
         * Observe the user points and display it
         */
        observe(viewModel.userPoints) { userPoints ->
            val gpaScoreText = getString(R.string.gpa_score)
            val pointsText = String.format(getString(R.string.gpa_format), userPoints)

            binding.gpaTextView.setColoredTextWithPrefix(
                gpaScoreText,
                pointsText,
                ContextCompat.getColor(requireContext(), R.color.yellow_primary)
            )
        }
    }

    private fun setNavigation() {
        with(binding) {
            blueGpaVectorView.setOnClickListener {
                viewModel.setNavigation(
                    QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizPointsFragment(
                        currentUser!!
                    )
                )
            }
            logOutButton.setOnClickListener {
                showLogOutDialog()
            }
        }
    }

    private fun showLogOutDialog() {
        showDialog(
            R.layout.dialog_listener,
            getString(R.string.leaving_question),
            onPositiveButtonClick = {
                lifecycleScope {
                    viewModel.clearUserSession()
                }
            })
    }

    private fun setObserver() {
        binding.startQuizRecyclerView.adapter = adapter
        observe(viewModel.quizData) {
            adapter.submitList(it)
        }
        viewModel.fetchQuizData()
    }
}