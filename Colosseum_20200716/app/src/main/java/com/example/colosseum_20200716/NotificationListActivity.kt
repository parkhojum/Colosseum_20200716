package com.example.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.colosseum_20200716.BaseActivity
import com.example.colosseum_20200716.R
import com.example.colosseum_20200716.adapters.NotificationAdapter
import com.example.colosseum_20200716.datas.Notification
import com.example.colosseum_20200716.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_notification_list.*
import org.json.JSONObject

class NotificationListActivity : BaseActivity() {

    val mNotiList = ArrayList<Notification>()
    lateinit var mNotiAdaper : NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)
        setupEvents()
        setValues()

    }
    override fun setupEvents() {

    }

    override fun setValues() {

        getNotiListFromServer()

        mNotiAdaper = NotificationAdapter(mContext,R.layout.notification_list_item,mNotiList)
        notiLisView.adapter = mNotiAdaper

    }

    fun getNotiListFromServer(){

        ServerUtil.getRequestNotificationList(mContext,object :ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val notifications = data.getJSONArray("notifications")

                for (i in 0 until  notifications.length()){
//                    JSONArray 내부의 JSONObject 추출 => Notifi
                    mNotiList.add(Notification.getNotificationFromJson(notifications.getJSONObject(i)))
                }

                runOnUiThread {
//                    어댑터 새로고침
                    mNotiAdaper.notifyDataSetChanged()


                }

            }

        })

    }

}