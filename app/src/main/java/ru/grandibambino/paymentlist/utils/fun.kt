package ru.grandibambino.paymentlist.utils

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.grandibambino.paymentlist.R

fun logDebug(tag: String, message: String){
    Log.d(tag, message)
}

fun logError(tag: String, message: String){
    Log.e(tag, message)
}

fun Fragment.navigate(): NavController =
    NavHostFragment.findNavController(this)

fun Fragment.toast(message: String){
    Toast.makeText(
        activity,
        message,
        Toast.LENGTH_SHORT
    ).show()
}
