package com.example.pizza_20200714.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.pizza_20200714.datas.PizzStore

class PizzStoreAdapter(val mComtext: Context, val resId:Int, val mList :List<PizzStore>) :
    ArrayAdapter<PizzStore>(mComtext,resId,mList) {

    val inf = LayoutInflater.from(mComtext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow = )