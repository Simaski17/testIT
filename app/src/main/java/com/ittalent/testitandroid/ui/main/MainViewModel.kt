package com.ittalent.testitandroid.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.antonioleiva.mymovies.ui.common.ScopedViewModel
import com.ittalent.data.source.LocalDataSource
import com.ittalent.domain.ItunesSongs
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.postException
import com.ittalent.testitandroid.ui.common.postLoading
import com.ittalent.testitandroid.ui.common.postValue
import com.ittalent.usecases.GetListSongsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getListSongsUseCase: GetListSongsUseCase): ScopedViewModel() {

    val model = MutableLiveData<Data<ArrayList<ItunesSongs>>>()

    init {
        initScope()
    }


    fun getListSongs(song: String, offset: Int){
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                    getListSongsUseCase.invoke(song, offset)
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()){
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: ${response.isEmpty().toString()}"))
                }

            }.onFailure { throwable ->
                model.postException(throwable)
            }

        }
    }


}