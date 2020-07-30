package com.example.colosseum_20200716

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.colosseum_20200716.adapters.TopicAdater
import com.example.colosseum_20200716.datas.Topic
import com.example.colosseum_20200716.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mTopicList = ArrayList<Topic>()

    lateinit var mTopicAdapter : TopicAdater


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

//        알림버튼을 누르면 => 알림 목록 화면으로 이동
        notificationBtn.setOnClickListener {

//            이미지뷰도 => setOnClickListener 설정 가능

            val myIntent = Intent(mContext, NotificationListActivity::class.java)
            startActivity(myIntent)



        }

        topicListView.setOnItemClickListener { parent, view, position, id ->

            val clickedTopic = mTopicList[position]





            val myIntent = Intent(mContext,ViewTopicDetallActivity::class.java)
            myIntent.putExtra("topicId",clickedTopic.id)
            startActivity(myIntent)




        }

    }

    override fun setValues() {

        getTopicListFromServer()

        mTopicAdapter = TopicAdater(mContext,R.layout.topic_list_item,mTopicList)
        topicListView.adapter = mTopicAdapter

//        BaseActivity가 돌려주는 => 알림 버튼을
        notificationBtn.visibility = View.VISIBLE



    }


    
//    알림의 갯수만 가져오는 API 호출

    fun getNotiCountFromServer() {
        ServerUtil.getRequestNotificationCount(mContext, object :ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")

                val unreadNotiCount = data.getInt("unread_noty_count")

//

                runOnUiThread {

                    if (unreadNotiCount == 0) {

                        notiCountTxt.visibility = View.GONE

                    }
                    else{

                        notiCountTxt.visibility = View.VISIBLE
                        notiCountTxt.text = unreadNotiCount.toString()
                    }
                }

            }

        })

    }

    override fun onResume() {
        super.onResume()
        getNotiCountFromServer()
    }

    fun getTopicListFromServer() {

        ServerUtil.getRequestMainInfo(mContext,object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")

//                topics는 [] 임. => JSONArray 로 추출해야함
                val topics = data.getJSONArray("topics")

//                topics 내부에는 JSONObject 가 여러개 반복으로 들어 있다.
//                JSON을 들고 있는 배열 => JSONArray

//                for문을 이용해서, topics 내부의 데이터를 하나씩 추출.
//                i가0부터 ~topics의 갯수 직전 4개 :(0,1,2,3,)

                for (i in 0 until  topics.length()){

//                topics 내부의 데이터를 JSONObect 로 추출
                    val topicObj = topics.getJSONObject(i)

//                topicObj => Topic 형태의 객체로 변환

                val topic = Topic.getTopicFromJson(topicObj)

//
//                변환돤 객체를 목록에 추가
                    mTopicList.add(topic)
            }

//             for 문으로 주제 목록을 모두 추가하고 나면
//                리스트뷰의 내용이 바꼈다고 새로고침

                runOnUiThread {
                    mTopicAdapter.notifyDataSetChanged()
                }

            }

        })

    }


}