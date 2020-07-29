package com.example.colosseum_20200716.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.colosseum_20200716.R
import com.example.colosseum_20200716.datas.Notification
import com.example.colosseum_20200716.datas.Reply

class NotificationAdapter(val mContext: Context, val resId : Int, val mList: List<Notification>):
    ArrayAdapter<Notification>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {

            tempRow = inf.inflate(R.layout.activity_notification_list, null)

        }

        val row = tempRow!!

        return row
    }


}