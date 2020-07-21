package com.example.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.colosseum_20200716.datas.Topic
import com.example.colosseum_20200716.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_topic_detall.*
import org.json.JSONObject

class ViewTopicDetallActivity : BaseActivity() {
    var mTopicId = 0

//    서버에서 받아오는 토론 정보를 저장할 맴버변수
    lateinit var mTopic : Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detall)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {


    }

    override fun setValues() {

//        메인에서 넘겨준 id 값을 맴버 변수에 저장
//        만약 0 이 저장되었다면 => 오류가 있는 상황으로 간주하자
        mTopicId = intent.getIntExtra("topicId", 0)

        if (mTopicId == 0) {
            Toast.makeText(mContext,"주제 상세 id에 문제가 있습니다", Toast.LENGTH_SHORT).show()
        }

//        서버에서 토론 주제에 대한 상세 진행 상황 가져오기
        getTopicDateilFromServer()
    }

    fun getTopicDateilFromServer(){

        ServerUtil.getRequestTopicDetail(mContext,mTopicId,object :ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")

                val topicObj = data.getJSONObject("topic")

//                서버에서 내려주는 주제는 주제 정보를
//                Topic 객체로 변환해서맴버 변수에 저장

                mTopic = Topic.getTopicFromJson(topicObj)

//                화면에 토론 관련 정보 표시
                runOnUiThread {

                    topicTitleTxt.text = mTopic.title
                    Glide.with(mContext).load(mTopic.imageUrl).into(topicImg)

                }

            }

        })

    }

}