package ru.grandibambino.paymentlist.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import ru.grandibambino.paymentlist.R
import ru.grandibambino.paymentlist.utils.APP_TOKEN

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initField()
        startFragment()
    }

    private fun initField(){
        APP_TOKEN = ""
    }

    private fun startFragment(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
    }

}