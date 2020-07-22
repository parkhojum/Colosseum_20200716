package com.example.colosseum_20200716.datas

import org.json.JSONObject

class User {

    var id = 0
    var email = " "
    var nickname = " "

    companion object{



        fun getUserFromJson(json: JSONObject) : User {
            val u = User()

            //            사용자 정보를 파싱하는 코드
            u.id = json.getInt("id")
            u.email = json.getString("email")
            u.nickname = json.getString("nick_name")




            return u

        }

    }

}