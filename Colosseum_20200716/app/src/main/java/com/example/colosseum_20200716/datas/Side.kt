package com.example.colosseum_20200716.datas

import org.json.JSONObject

class Side {
    var id = 0
    var title = " "
    var voteCout = 0

    companion object{

//        json을 넣으면 side 로 변환해주는 기능
        fun getSideFromJson(json:JSONObject) : Side {
    val s = Side()

//    s의 데이터들을 json을 이용해서 채워주자
    s.id = json.getInt("id")
    s.title = json.getString("title")
    s.voteCout = json.getInt("vote_count")

    return s
}

    }

}