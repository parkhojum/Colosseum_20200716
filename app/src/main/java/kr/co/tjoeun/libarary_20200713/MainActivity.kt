package kr.co.tjoeun.libarary_20200713

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }


    override fun setupEvents() {
        goToPhotoViewRtn.setOnClickListener {
            val myIntent = Intent(mContext, ProfilePhotoActivity::class.java)
            startActivity(myIntent)

        }

        callBtn.setOnClickListener {

            val phoneNum = phoneNumTxt.text.toString()

            val myUri = Uri.parse("tel:${phoneNum}")
            val myIntent = Intent(Intent.ACTION_CALL, myUri)
            startActivity(myIntent)
        }

    }

    override fun setValues() {
        val imgUrl = "https://dimg.donga.com/wps/NEWS/IMAGE/2019/10/21/97986591.2.jpg"
        Glide.with(mContext).load(imgUrl).into(profileImg)

    }
}