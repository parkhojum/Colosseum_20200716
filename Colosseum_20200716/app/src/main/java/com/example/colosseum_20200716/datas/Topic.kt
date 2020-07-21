package com.example.colosseum_20200716.datas

import org.json.JSONObject
import java.util.ArrayList

class Topic {
    var id = 0
    var title = " "
    var imageUrl = " "

//    주제는 선택 진영 목록을 하위 정보로 갖는다.
    val sideList = ArrayList<Side>()

    companion object{

        //    json 한 덩어리를 넣으면 => Topic객체로 변환해주는 기능

        fun getTopicFromJson(json:JSONObject) : Topic {

//            변환시켜줄 Topic 객체 생성
            val topic = Topic()



//            만든 객체의 내용물을 json을 이용해서 채우자
            topic.id = json.getInt("id")
            topic.title = json.getString("title")
            topic.imageUrl = json.getString("img_url")


//            side 배열에 들어있는, 진영 선택 정보도 넣어줘야함


            val sides = json.getJSONArray("sides")

//            받아낸 jsonArray 내부를 스캔
            for (i in 0 until sides.length()){

//                진영 정보를 하나씩 파싱해서 => 토론의 진영 목록에 추가
                val sideObj = sides.getJSONObject(i)
                val side = Side.getSideFromJson(sideObj)

                topic.sideList.add(side)

            }

//            완성된 객체를 리턴
            return topic

        }



    }



}