package com.example.colosseum_20200716.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.colosseum_20200716.R
import com.example.colosseum_20200716.ViewReplyDetaActivity
import com.example.colosseum_20200716.datas.Reply
import com.example.colosseum_20200716.datas.Topic
import com.example.colosseum_20200716.utils.TimeUtil
import java.text.SimpleDateFormat

class ReReplyAdapter (val mContext: Context, val resId : Int, val mList: List<Reply>)
    : ArrayAdapter<Reply>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.re_reply_list_item, null)
        }

        val row = tempRow!!

        val writerNickNameTxt = row.findViewById<TextView>(R.id.writerNickNameTxt)
        val selectedSideTitleTxt = row.findViewById<TextView>(R.id.selectedSideTitleTxt)
        val replyWriteTimeTxt = row.findViewById<TextView>(R.id.replyWriteTimeTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val likeBtn = row.findViewById<TextView>(R.id.likeBtn)
        val dislikeBtn = row.findViewById<TextView>(R.id.dislikeBtn)

        val data = mList[position]

        writerNickNameTxt.text = data.writer.nickname

        selectedSideTitleTxt.text = "(${data.selectedSide.title})"

        replyWriteTimeTxt.text = TimeUtil.getTimeAgoFromCalendar(data.weittenDateTime)

        contentTxt.text = data.content

        likeBtn.text = "좋아요${data.likeCount}"
        dislikeBtn.text = "싫어요${data.dislikeCount}"

        return row
    }



}