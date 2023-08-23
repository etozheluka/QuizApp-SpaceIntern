package com.space.quizapp.utils.extensions

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.space.quizapp.R

fun Fragment.showDialog(
    layoutResId: Int,
    messageText: String,
    cancelable:Boolean = true,
    onPositiveButtonClick: () -> Unit,

) {
    val dialog = Dialog(requireContext())
    dialog.setContentView(layoutResId)

    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT
    )

    val messageTextView = dialog.findViewById<TextView>(R.id.descriptionTextView)
    val yesButton = dialog.findViewById<Button>(R.id.yesButton)
    val noButton = dialog.findViewById<Button>(R.id.noButton)
    dialog.setCancelable(cancelable)

    messageTextView.text = messageText


    yesButton.setOnClickListener {
        dialog.dismiss()
        onPositiveButtonClick.invoke()
    }

    noButton.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}