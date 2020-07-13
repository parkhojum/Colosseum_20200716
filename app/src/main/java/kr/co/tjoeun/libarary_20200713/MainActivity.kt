package kr.co.tjoeun.libarary_20200713

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
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

            //onPermissionGranted로 옮겨줌
//            val phoneNum = phoneNumTxt.text.toString()
//
//            val myUri = Uri.parse("tel:${phoneNum}")
//            val myIntent = Intent(Intent.ACTION_CALL, myUri)
//            startActivity(myIntent)


            //TedPermission을 이용해서 통화 권한을 허락할건지 질문.

            //권한 승인/거절에 따라 어떤 행동을 할지 할일을 적어둔 변수.
            val permissionListener = object : PermissionListener{
                override fun onPermissionGranted() {

                    //PermissionGranted() : 승인을 받은 상황. -> 실제로 전화를 걸자
                    val phoneNum = phoneNumTxt.text.toString()

                    val myUri = Uri.parse("tel:${phoneNum}")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

                    //PermissionDenied : 최종 거부된 상황

                    Toast.makeText(mContext,"통화 권한이 거부되어 연결 불가합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun setValues() {
        val imgUrl = "https://dimg.donga.com/wps/NEWS/IMAGE/2019/10/21/97986591.2.jpg"
        Glide.with(mContext).load(imgUrl).into(profileImg)

    }
}