package com.example.pizza_202007142

import androidx.appcompat.app.AppCompatActivity


abstract class BaesActivity :AppCompatActivity() {

    val mContext = this
    abstract fun setupEvents()
    abstract fun setValues()
}