package com.android.letsgo.viewModel

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

import androidx.lifecycle.ViewModel
import com.android.letsgo.R
import com.android.letsgo.db.MeasureData
import com.android.letsgo.utils.ReadRXFirebaseUtil
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

class MeasureAdsViewModel : ViewModel() {

    private val database = FirebaseFirestore.getInstance()
    private val itemsRef = database.collection("MeasureData")

    var measureData by mutableStateOf<List<MeasureData>>(emptyList())
    var measures by mutableStateOf<List<MeasureData>>(emptyList())

    init{ fetchBook() }

    private fun fetchBook() {

        val measure: MutableList<MeasureData> = ArrayList()

        ReadRXFirebaseUtil.observeValueEvent(itemsRef)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<QuerySnapshot> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(documentSnapshots: QuerySnapshot) {
                    val data: MutableList<MeasureData> = ArrayList()
                    for (document in documentSnapshots.documents) {
                        val nameMeasure = document.getString("name_measure")
                        val placeMeasure = document.getString("place_measure")
                        val dateStartMeasure = document.getString("date_start_measure")
                        val timeStartMeasure = document.getString("time_start_measure")
                        val descriptionMeasure = document.getString("description_measure")
                        val coastMeasure = document.getString("coast_measure")
                        val lat = document.getString("lat")
                        val lon = document.getString("lon")
                        val imgUrl = document.getString("imgUrl")
                        if(nameMeasure!=null && placeMeasure!=null && dateStartMeasure!=null && timeStartMeasure!=null && descriptionMeasure!=null &&
                            coastMeasure!=null  && lat !=null && lon !=null && imgUrl!=null)
                            data.add(MeasureData(nameMeasure, placeMeasure, dateStartMeasure, timeStartMeasure,
                                descriptionMeasure, coastMeasure, lat.toFloat(),lon.toFloat(), imgUrl))

                    }

                    measureData = data
                    for(i in measureData.indices){
                        measure.add(measureData[i])
                    }
                    measures = measure
                }
                override fun onError(e: Throwable) {
                    RxJavaPlugins.onError(e)
                }
                override fun onComplete() {}
            })

    }

    @Composable
    fun MeasureViewScreen(){
        val back = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        val currentList by remember { mutableStateOf(measures) }
        Column(
            Modifier
                .fillMaxSize()
                .background(colorResource(R.color.color_for_background)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(Modifier.fillMaxSize()) {
                IconButton(onClick = {back?.onBackPressed() }) {
                    Image(
                        painter = painterResource(id = R.drawable.leftarrow),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .size(30.dp)
                    )
                }

            }
            MeasureAdsLazyScreen(data = currentList)
        }
    }

    @Composable
    fun MeasureAdsLazyScreen(data: List<MeasureData>){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(2.dp),
            modifier = Modifier.background(colorResource(R.color.color_for_background))
        ){
            items(data.size){
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .background(colorResource(R.color.color_for_background)),
                    shape = RoundedCornerShape(15.dp)
                ){
                    Column(modifier = Modifier
                        .fillMaxSize()) {
                        Box {
                            Column(modifier = Modifier.padding(
                                start = 8.dp,
                                top = 4.dp,
                                bottom = 8.dp)) {
                                Row {
                                    Text(data[it].name_measure,
                                        fontSize = 23.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(
                                            start = 50.dp,
                                            top = 5.dp
                                        ))
                                }
                                Row {
                                    Text(data[it].time_start_measure,
                                        fontSize = 23.sp,
                                        modifier = Modifier.padding(start = 4.dp))
                                }
                                Row {
                                    Text(data[it].place_measure,
                                        fontSize = 23.sp,
                                        modifier = Modifier.padding(start = 4.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMeasureAdsScreen(){
        MeasureViewScreen()
    }
}

