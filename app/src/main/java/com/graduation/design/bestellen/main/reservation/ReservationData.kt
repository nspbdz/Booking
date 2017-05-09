package com.graduation.design.bestellen.main.reservation

import android.util.Log
import com.graduation.design.bestellen.data.RemoteDataRepository
import com.graduation.design.bestellen.model.RoomDetail
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET

/**
 * Created by pan on 2017/5/6.
 * data
 */
class ReservationData : RemoteDataRepository() {
    fun loadData(callback: (dataList: List<RoomDetail>) -> Unit): Unit {
        val service = mRetrofit.create(GetRoomDetail::class.java)

        service.getRoomDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            callback(response.body())
                        },
                        { e ->
                            Log.e("exception", e.message)
                        }
                )
    }

    interface GetRoomDetail {
        @GET("/rooms")
        fun getRoomDetail(): Observable<Response<List<RoomDetail>>>
    }
}