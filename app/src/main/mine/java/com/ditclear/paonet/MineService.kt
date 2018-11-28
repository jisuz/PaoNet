package com.ditclear.paonet

import android.arch.lifecycle.ViewModel
import com.ditclear.paonet.di.component.DaggerMineComponent
import javax.inject.Inject
import javax.inject.Provider

/**
 * 页面描述：SearchService
 *
 * Created by ditclear on 2018/11/28.
 */
class MineService :CoreService{

    @Inject
    lateinit var creators:Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

    override fun init(app: PaoApp) {
        DaggerMineComponent.builder().appModule(app.appModule).build().inject(this)
        app.factory.plusMap(creators)
    }

    override val serviceName: String = "mine"

}