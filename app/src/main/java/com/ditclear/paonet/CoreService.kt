package com.ditclear.paonet

/**
 * 页面描述：CoreService
 *
 * Created by ditclear on 2018/11/28.
 */
interface CoreService{

    val serviceName:String

    fun init(app:PaoApp)
}