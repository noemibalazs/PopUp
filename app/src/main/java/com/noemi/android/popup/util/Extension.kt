package com.noemi.android.popup.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

fun AppCompatActivity.showDialogFragment(dialogFragment: DialogFragment, tag: String) {
    val transaction = this.supportFragmentManager.beginTransaction()
    val fragment = this.supportFragmentManager.findFragmentByTag(tag)
    fragment?.let {
        transaction.remove(it)
    }
    dialogFragment.show(transaction, tag)
}