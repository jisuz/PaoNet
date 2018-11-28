package com.ditclear.paonet.view.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentStatePagerAdapter
import com.ditclear.paonet.R
import com.ditclear.paonet.databinding.HomeFragmentBinding
import com.ditclear.paonet.view.base.BaseFragment
import com.ditclear.paonet.view.home.viewmodel.ToTopOrRefreshContract
import javax.inject.Inject
import javax.inject.Named

/**
 * 页面描述：首页
 *
 * Created by ditclear on 2017/9/30.
 */
//@FragmentScope
class HomeFragment : BaseFragment<HomeFragmentBinding>() {


    @Inject
    @field:Named("home")
    lateinit var pagerAdapter: FragmentStatePagerAdapter

    companion object {

        fun newInstance() = HomeFragment()
    }

    override fun getLayoutId(): Int = R.layout.home_fragment

    override fun loadData(isRefresh: Boolean) {

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getComponent().inject(this)

    }

    override fun initArgs(savedInstanceState: Bundle?) {

    }


    fun toTopOrRefresh() {
        pagerAdapter.getItem(mBinding.viewPager.currentItem).let {
            if (it is ToTopOrRefreshContract) {
                it.toTopOrRefresh()
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            show()
        }
    }

    fun show() {
        (activity as MainActivity).needShowTab(true)
        (activity as MainActivity).setupWithViewPager(mBinding.viewPager)
    }

    override fun initView() {
        mBinding.viewPager.offscreenPageLimit = pagerAdapter.count
        mBinding.viewPager.adapter = pagerAdapter
        show()
    }


}