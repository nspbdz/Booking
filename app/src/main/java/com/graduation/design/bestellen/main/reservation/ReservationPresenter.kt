package com.graduation.design.bestellen.main.reservation

/**
 * Created by pan on 2017/5/6.
 * reservation presenter
 */
class ReservationPresenter(view: ReservationContract.View, data: ReservationData) : ReservationContract.Presenter {
    val mView: ReservationContract.View = view
    val mData: ReservationData = data

    init {
        mView.setPresenter(this)
    }

    override fun start() {
    }

    override fun initData() {
        mData.loadData{dataList ->
            mView.getDataSet()?.addAll(dataList)
            mView.updateRecyclerView()
            mView.hideLoading()
        }
    }

    override fun loadMoreData() {
        mView.hideLoading()
    }
}