package com.graduation.design.bestellen.main.reservation

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.graduation.design.bestellen.base.BaseFragment
import com.graduation.design.bestellen.model.RoomDetail
import com.graduation.design.bestellen.roomdetail.RoomDetailActivity
import kotlinx.android.synthetic.main.fragment_reservation.*

/**
 * Created by pan on 2017/5/4.
 * reservationFragment, show all rooms at first(17/5/6)
 */
class ReservationFragment : BaseFragment(), ReservationContract.View {

    var mAdapter: ReservationAdapter? = null

    val mPresenter: ReservationPresenter = ReservationPresenter(this, ReservationData())

    //from view interface
    override fun setPresenter(presenter: ReservationContract.Presenter) {
    }

    override fun showLoading() {
        recyclerView.setRefreshing(true)
    }

    override fun hideLoading() {
        recyclerView.setRefreshing(false)
    }

    override fun updateRecyclerView() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun getDataSet(): MutableList<RoomDetail>? = mAdapter?.getDataSet()

    //from baseFragment
    override fun initData() {
        mPresenter.initData()
    }

    override fun getLayout(): Int = com.graduation.design.bestellen.R.layout.fragment_reservation

    override fun initViews() {
        mAdapter = ReservationAdapter(activity, ArrayList())
        mAdapter?.setOnItemClickListener { _, position ->
            val detail = mAdapter?.mDataSet?.get(position)
            startActivity<RoomDetailActivity>(Pair("detail", detail as RoomDetail))
        }
        recyclerView.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL), 0)
        recyclerView.setAdapter(mAdapter)
        recyclerView.setOnRefreshListener {
            mPresenter.loadMoreData()
        }

    }
}