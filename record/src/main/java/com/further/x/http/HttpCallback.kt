package com.further.x.http

/**
 * Created by Zion
 * 2019/12/3.
 */
interface HttpCallback {
     fun success(response : String)
     fun fail()
}