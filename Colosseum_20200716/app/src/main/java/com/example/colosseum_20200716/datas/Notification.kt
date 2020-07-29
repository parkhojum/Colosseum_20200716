package com.example.colosseum_20200716.datas

import org.json.JSONObject
import java.util.*

class Notification {

    var id = 0
    var title = ""

//
    val createAtCal = Calendar.getInstance()

    companion object{

        fun getNotificationFromJson(json : JSONObject) : Notification{
            val n = Notification

            n.id = json.getInt("id")

            return n

        }

    }

}

