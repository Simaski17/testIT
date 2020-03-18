package com.ittalent.testitandroid.ui.common

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ittalent.testitandroid.ItunesApp

/* Live data */

fun <T> MutableLiveData<Data<T>>.repostValue(exec: T.() -> Unit = { }) = postValue(value?.apply { data?.apply(exec) })

fun <T> MutableLiveData<Data<T>>.postException(throwable: Throwable) {
    postValue(Data(dataState = DataState.ERROR, exception = throwable))
}

fun <T> MutableLiveData<Data<T>>.postSuccess() {
    postValue(Data(dataState = DataState.SUCCESS, data = null))
}

fun <T> MutableLiveData<Data<T>>.postValue(value: T) {
    postValue(Data(dataState = DataState.SUCCESS, data = value))
}

fun <T> MutableLiveData<Data<T>>.postLoading() {
    postValue(Data(dataState = DataState.LOADING))
}


/* Model View View-Model */

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}

/* Utils */

fun <T> T?.with(block: T.() -> Unit) = this?.let(block)

inline fun <T> T?.notNull(exec: (T) -> Unit): T? = this?.also { exec(this) }

val Context.app: ItunesApp
    get() = applicationContext as ItunesApp