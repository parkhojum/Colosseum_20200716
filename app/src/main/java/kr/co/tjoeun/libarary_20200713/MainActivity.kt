package kr.co.tjoeun.libarary_20200713

import android.content.Intent
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

    }

    override fun setValues() {
        val imgUrl = "https://dimg.donga.com/wps/NEWS/IMAGE/2019/10/21/97986591.2.jpg"
        Glide.with(mContext).load(imgUrl).into(profileImg)

    }
}