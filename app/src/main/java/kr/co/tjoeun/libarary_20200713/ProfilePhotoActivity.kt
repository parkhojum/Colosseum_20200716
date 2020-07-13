package kr.co.tjoeun.libarary_20200713

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class ProfilePhotoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_photo)

        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        goToPhotoViewRtn.setOnClickListener {
            val myIntent = Intent(mContext,ProfilePhotoActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {

    }


}