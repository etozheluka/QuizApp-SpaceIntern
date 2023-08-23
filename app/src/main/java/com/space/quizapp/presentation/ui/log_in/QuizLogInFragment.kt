package com.space.quizapp.presentation.ui.log_in


import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentLoginBinding
import com.space.quizapp.presentation.model.UserUIModel
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.utils.extensions.lifecycleScope
import com.space.quizapp.utils.extensions.observe
import com.space.quizapp.utils.extensions.viewBinding
import kotlin.reflect.KClass

class QuizLogInFragment : QuizBaseFragment<QuizLogInViewModel>() {
    override val layout: Int
        get() = R.layout.fragment_login
    override val viewModelClass: KClass<QuizLogInViewModel>
        get() = QuizLogInViewModel::class

    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onBind() {
        authorizeUser()
        setSessionObserver()
    }

    /**
     * Observe the session, if session is not empty navigate to home fragment
     */
    private fun setSessionObserver() {
        with(viewModel) {
            observeSession()
            observe(session) {
                if (it?.isNotEmpty() == true) {
                    setNavigation(QuizLogInFragmentDirections.actionQuizLogInFragmentToQuizHomeFragment())
                }
            }
        }
    }

    /**
     * Authorize the user
     */
    private fun authorizeUser() {
        with(binding) {
            logInButton.setOnClickListener {
                val username = inputUserNameEditText.text.toString()
                if (username.isNotEmpty()) {
                    viewModel.authorizeUser(UserUIModel(username = username))
                }
                setObserver()
            }
        }
        setObserver()
    }

    /**
     * Observe the error status
     */
    private fun setObserver() {
        observe(viewModel.errorStatus){
            binding.inputLayout.error = getString(it.toString().toInt())
        }
    }
}