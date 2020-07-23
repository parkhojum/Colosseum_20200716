package com.example.colosseum_20200716.datas

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Reply {

        var id = 0
    var  content = " "
    lateinit var writer : User
    lateinit var selectedSide : Side

//    의견이 작성된 시간을 담는 변수
    val weittenDateTime = Calendar.getInstance()

//    좋아요 / 싫어요 / 답글 갯수를 저장할 변수들
    var likeCount =0
    var dislikeCount = 0
    var replyCount =0

    companion object{

//        JSONObje

        fun getReplyFromJson(json : JSONObject) : Reply {
            val r = Reply()

            r.id = json.getInt("id")
            r.content = json.getString("content")



//            작성자 /선택진영 => JSONObject를 받아 곧바로 대입
            r.writer = User.getUserFromJson(json.getJSONObject("user"))

            r.selectedSide = Side.getSideFromJson(json.getJSONObject("selected_side"))

//            작성일시를 서버가 주는 내용을 분석해서 대입하자.

            val sdf = SimpleDateFormat("yyy-MM-dd HH:mm:ss")

//            서버가 주는 내용을 변수로 저장
            val createdAtString = json.getString("created_at")

//            맴버변수인 CaLendar변수에게 데이터 적용
            r.weittenDateTime.time = sdf.parse(createdAtString)

//            핸드폰의 시간대와, 서버의 시간대의 "시차를 구해서"
//            작성 일시의 시간값을 조정

//            내폰 의 시간대가 어디 시간대인지 변수로 저장
            val myPhoneTimeZone = r.weittenDateTime.timeZone //한국폰: 한국시간대

//            (서버랑) 몇시간 차이가 나는지 변수로 저장. => 밀리초까지 계산된 시차 => 시간단위로 변경
            val timeOffoset = myPhoneTimeZone.rawOffset / 1000 / 60 /60

//            게시글 작성시간을 timeOffset만큼 시간값을 더해주자.
            r.weittenDateTime.add(Calendar.HOUR, timeOffoset)

//            좋아요 / 싫어요 / 답글 갯수 실제 파싱 저장

            r.likeCount = json.getInt("like_count")
            r.dislikeCount = json.getInt("dislike_count")
            r.replyCount = json.getInt("reply_count")



            return r

        }



    }


}