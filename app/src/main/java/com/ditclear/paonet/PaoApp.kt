package com.ditclear.paonet

import android.app.Application
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.ditclear.paonet.di.component.AppComponent
import com.ditclear.paonet.di.component.DaggerAppComponent
import com.ditclear.paonet.di.module.AppModule
import com.ditclear.paonet.helper.SpUtil
import com.ditclear.paonet.helper.network.NetMgr
import com.ditclear.paonet.model.remote.BaseNetProvider
import com.ditclear.paonet.viewmodel.APPViewModelFactory
import es.dmoral.toasty.Toasty
import java.util.*
import javax.inject.Inject

/**
 * 页面描述：
 *
 * Created by ditclear on 2017/9/26.
 */

class PaoApp : Application() {

    lateinit var component: AppComponent
        private set

    companion object {
        private var instance: Application? = null
        fun instance() = instance?:throw Throwable("instance 还未初始化")
    }

    @Inject
    lateinit var factory: APPViewModelFactory

    val appModule by lazy { AppModule(this) }

    override fun onCreate() {
        super.onCreate()
        instance=this
        if (BuildConfig.DEBUG) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog()     // Print log
            ARouter.openDebug()   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(instance);

        NetMgr.registerProvider(BaseNetProvider(this))
        SpUtil.init(this)
        component = DaggerAppComponent.builder().appModule(appModule).build()
        component.inject(this)

        Toasty.Config.getInstance().apply(); // required

        val loader = ServiceLoader.load(CoreService::class.java)
        loader.iterator().forEach {
            Log.d("PaoNet-----",it.serviceName)
            it.init(this)
        }
    }
}
