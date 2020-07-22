package com.example.colosseum_20200716.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.colosseum_20200716.R
import com.example.colosseum_20200716.datas.Reply
import com.example.colosseum_20200716.datas.Topic

class ReplyAdapter (val mContext: Context, val resId : Int, val mList: List<Reply>)
    : ArrayAdapter<Reply>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.reply_list_ltem, null)
        }

        val row = tempRow!!

        val writerNickNameTxt = row.findViewById<TextView>(R.id.writerNickNameTxt)
        val selectedSideTitleTxt = row.findViewById<TextView>(R.id.secondSideCountTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)

        val data = mList[position]

        writerNickNameTxt.text = data.writer.nickname
        selectedSideTitleTxt.text = "(${data.selectedSide.title})"
        contentTxt.text = data.content

        return row
    }



}