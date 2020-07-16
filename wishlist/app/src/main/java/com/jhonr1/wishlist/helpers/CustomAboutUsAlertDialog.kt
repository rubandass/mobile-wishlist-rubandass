package com.jhonr1.wishlist.helpers

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.jhonr1.wishlist.R

/**
 * About Us class. It contains basic information about the app developers
 */
class CustomAboutUsAlertDialog(private val context: Activity) : DialogFragment() {
    private lateinit var alertDialog: AlertDialog
    private val builder = AlertDialog.Builder(context)

    /**
     * shows app developer information in a alert dialog
     */
    fun show(){
        val messageList = context.resources.getStringArray(R.array.about_us_message)
        builder.setTitle(context.resources.getString(R.string.about_us))
        builder.setIcon(R.drawable.wishlist_logo)
        builder.setCancelable(true)
        builder.setItems(messageList) { _, _ ->  }
        builder.setNegativeButton(R.string.about_us_close) { _, _ ->  }
        alertDialog = builder.setView(view).create()
        builder.show()
    }

}