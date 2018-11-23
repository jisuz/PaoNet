package com.ditclear.paonet.di.component

import com.ditclear.paonet.di.module.ActivityModule
import com.ditclear.paonet.di.scope.ActivityScope
import com.ditclear.paonet.view.article.ArticleDetailActivity
import com.ditclear.paonet.view.auth.LoginActivity
import com.ditclear.paonet.view.code.CodeDetailActivity
import com.ditclear.paonet.view.home.MainActivity
import dagger.Subcomponent

/**
 * 页面描述：ActivityComponent
 *
 * Created by ditclear on 2017/9/29.
 */
@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: ArticleDetailActivity)

    fun inject(activity: CodeDetailActivity)

    fun inject(activity: MainActivity)

    fun inject(activity: LoginActivity)

    fun supplyFragmentComponentBuilder():FragmentComponent.Builder

}