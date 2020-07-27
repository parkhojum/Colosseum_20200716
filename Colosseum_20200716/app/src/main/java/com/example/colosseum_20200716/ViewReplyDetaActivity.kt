package com.example.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.colosseum_20200716.datas.Reply
import com.example.colosseum_20200716.utils.ServerUtil
import com.example.colosseum_20200716.utils.TimeUtil
import kotlinx.android.synthetic.main.activity_view_reply_deta.*
import org.json.JSONObject

class ViewReplyDetaActivity : BaseActivity() {

//    보러는 의견의 id는 여러 함수에서 공유할 것 같다.
//    그래서 맴버변수로 만들고 저장한다.
    var mReplyId = 0

    // 이 화면에서 보여줘야할 의견의 정보를 가진 변수 => 맴버 변수
    lateinit var mReply : Reply

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_deta)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {



    }

    override fun setValues() {

//        의견 리스트뷰에서 보내주는 id값을 맴버변수에 담아주자.
        mReplyId = intent.getIntExtra("replyId",0)

//        해당 id값에 맞는 의견 정보를 (서버에서) 다시 불러오자

        getReplyFromServer()

    }

//    서버에서 의견 정보 불러오가
    fun getReplyFromServer(){

    ServerUtil.getRequestReplyDetail(mContext, mReplyId, object :ServerUtil.JsonResponseHandler{
        override fun onResponse(json: JSONObject) {

            val data = json.getJSONObject("data")
            val reply = data.getJSONObject("reply")

//            replyobj 를 Reply클레스로 변환 =>

            mReply = Reply.getReplyFromJson(replyObj)

//            mReply 내부의 변수(정보) 들을 => 화면에 반영

            runOnUiThread {

                writerNickNameTxt.text = mReply.writer.nickname

                selectedSideTitleTxt.text = "(${mReply.selectedSide.title})"

                writtenDataTimeTxt.text = TimeUtil.getTimeAgoFromCalendar(mReply.weittenDateTime)

                replyContentTxt.text = mReply.content

            }

        }

    })

}


}