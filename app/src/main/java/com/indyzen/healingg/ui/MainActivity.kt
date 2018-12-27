package com.indyzen.healingg.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.indyzen.healingg.R
import com.indyzen.numberplatedetection.repository.networks.BuldRetrofit
import com.indyzen.numberplatedetection.repository.networks.RetrofitConfig
import com.indyzen.numberplatedetection.repository.networks.ServiceResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login()
    }


    private fun login() {
        val service = BuldRetrofit().getRetrofit()?.create(RetrofitConfig::class.java)
//        val fileUri = test
//        val file = File(getRealPathFromURI(fileUri))

//        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), path)
//        val source = MultipartBody.Part.createFormData("source", path.name, requestFile)
//        val image_type = RequestBody.create(okhttp3.MultipartBody.FORM, "DIRECT")
//        Log.e("source",source.toString())
//        Log.e("imageType",source.toString())

//        val requestText = RequestBody.create(MediaType.parse("multipart/form-data"), image_type)

        service?.login("sumonsen@innovination.com", "123456")?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Subscriber<ServiceResponse>(){
                override fun onNext(t: ServiceResponse?) {
                   Log.e("data",t!!.message)
                }

                override fun onCompleted() {
                    Log.e("onCompleted","Success")
                }

                override fun onError(e: Throwable?) {
                    Log.e("onError",e.toString())
                }
            })
    }
}
