package com.example.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

//        버튼이 눌리면 할 일을 변수에 담아서 저장.
//        TedPermlssion에서 권한별 할 일을 변수에 담아서 저장한것과 같은 논리

        val voteCode = View.OnClickListener{

//            it => View 형태 => 눌린 버튼을 담고 있는 변수.

            val clickedSideTag = it.tag.toString()

            Log.d("눌린 버튼의 태그",clickedSideTag)

//            눌린 버튼의 태그를 INT로 바꿔서
//            토론 주제의 진영중 어떤 진영을 눌렸는지 가져오는 index로 활용
            val clickedSide = mTopic.sideList[clickedSideTag.toInt()]

            Log.d("투표하려는 진영 제목",clickedSide.title)

//           실제로 해당 진영에 투표하기
            ServerUtil.postRequesVote(mContext, clickedSide.id,object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

//                   서버는 변경돤 결과가 어떻게 되는지 다시 내려줌
//                    이 응답에서, 토론 진행 현황을 다시 파싱해서
//                    화면에 반영

                    val data = json.getJSONObject("data")
                    val topic = data.getJSONObject("topic")

//                    맴버변수로 있는 토픽을 갈아주자

                    mTopic = Topic.getTopicFromJson(topic)

//                    화면에 mTopic의 데이터를 이용해서 반영
                    runOnUiThread {
                        setTopicDataToUi()
                    }

                }

            })

        }

//        두개의 투표하기 버튼이 눌리면 할 일을 모두 voteCode에 적힌 내용으로
         voteToFirstSideBtn.setOnClickListener(voteCode)
        voteToSecondSideBtn.setOnClickListener(voteCode)


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

                    setTopicDataToUi()


                }

            }

        })

    }

//    화면에 mTopic 기반으로 데이터 반영해주는 기능

    fun setTopicDataToUi() {
        topicTitleTxt.text = mTopic.title
        Glide.with(mContext).load(mTopic.imageUrl).into(topicImg)

//                    진영 정보도 같이 표시
        firstSideTitleTxt.text = mTopic.sideList[0].title
        secondSideTitleTxt.text = mTopic.sideList[1].title

        firstSideCountTxt.text = "${mTopic.sideList[0].voteCount}"
        secondSideCountTxt.text = "${mTopic.sideList[1].voteCount}"

//        내가 투표 했는지 or 어느 진영에 했는지에 따라 버튼 ux 변경
//         투표x : 두 버튼 모두 "투표하기" + 양진영 모두 클릭 가능
//        첫번째 진영 투표 : 첫 버튼 "투표 취소" , 두번째 진영 "갈아타기"
//        그외 : 두번째 진영 투표한걸 처리

//        투표 한 진영이 몇번째 진영인지? 파악해야함


        if (mTopic.getMySideIndex() == -1){
//            아직 투표를 안한 경우
            voteToFirstSideBtn.text = "투표하기"
            voteToSecondSideBtn.text = "투표하기"
        }
        else if (mTopic.getMySideIndex() == 0) {
//            첫 진영에 투표한 경우
            voteToFirstSideBtn.text = "투표 취소"
            voteToSecondSideBtn.text = "투표 취소"
        }
         else {
//            두번째 진영에 투표한 경우
            voteToFirstSideBtn.text = "갈아타기"
            voteToSecondSideBtn.text = "갈아타기"
        }
    }

}