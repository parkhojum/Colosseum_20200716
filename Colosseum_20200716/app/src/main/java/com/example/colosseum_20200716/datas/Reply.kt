package com.example.colosseum_20200716.datas

import org.json.JSONObject

class Reply {

        var id = 0
    var  content = " "
    lateinit var writer : User
    lateinit var selectedSide : Side

    companion object{

//        JSONObje

        fun getReplyFromJson(json : JSONObject) : Reply {
            val r = Reply()

            r.id = json.getInt("id")
            r.content = json.getString("content")



//            작성자 /선택진영 => JSONObject를 받아 곧바로 대입
            r.writer = User.getUserFromJson()
            r.selectedSide = Side.getSideFromJson(json.getJSONObject("s"))

            return r

        }



    }


}