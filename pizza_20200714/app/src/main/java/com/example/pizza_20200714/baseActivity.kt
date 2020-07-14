package com.example.pizza_20200714

import androidx.appcompat.app.AppCompatActivity

abstract class baseActivity : AppCompatActivity(){

    val mContext = this
    abstract fun setupEvents()
    abstract fun setValues()
}