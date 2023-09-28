package com.android.letsgo.presenter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.android.letsgo.db.MeasureData
import com.android.letsgo.fragment.MapsFragment
import com.android.letsgo.utils.ReadRXFirebaseUtil
import com.android.letsgo.view.GetMeasureView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


class GetInfoAdsPresenterKt {
    private var getMeasureView: GetMeasureView? = null
    var adds: List<MeasureData> = ArrayList()

    constructor(getMeasureView: MapsFragment){
        this.getMeasureView = getMeasureView
    }

    var adsMeasureData by mutableStateOf<List<MeasureData>>(emptyList())
    private val database = FirebaseFirestore.getInstance()
    private val items = database.collection("MeasureData")

    fun getMeasureInfo(){
        ReadRXFirebaseUtil.observeValueEvent(items).
        observeOn(AndroidSchedulers.mainThread()).
        subscribe(object :
            Observer<QuerySnapshot>{
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {
                RxJavaPlugins.onError(e)
            }

            override fun onComplete() {
            }

            override fun onNext(documentSnapshorts: QuerySnapshot) {
                val data:MutableList<MeasureData> = ArrayList()
                for (document in documentSnapshorts.documents){
                    val name_measure =document.getString("name_measure")
                    val place_measure = document.getString("place_measure")
                    val date_start_measure = document.getString("date_start_measure")
                    val time_start_measure = document.getString("time_start_measure")
                    val description_measure = document.getString("description_measure")
                    val coast_measure = document.getString("coast_measure")
                    val lat = document.getString("lat")
                    val lon = document.getString("lon")
                    val imgUrl = document.getString("imgUrl")
                    if(name_measure != null && place_measure != null && date_start_measure != null &&
                        time_start_measure != null && description_measure != null && coast_measure != null &&
                        lat != null && lon != null && imgUrl != null){
                        data.add(
                            MeasureData(
                                name_measure,
                                place_measure,
                                date_start_measure,
                                time_start_measure,
                                description_measure,
                                coast_measure,
                                lat.toFloat(),
                                lon.toFloat(),
                                imgUrl
                            )
                        )
                    }
                }
                adsMeasureData = data
                getMeasureView?.getInfoAds(adsMeasureData)
            }
        })
    }
}