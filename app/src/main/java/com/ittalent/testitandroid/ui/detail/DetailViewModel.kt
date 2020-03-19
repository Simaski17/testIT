package com.ittalent.testitandroid.ui.detail

import androidx.lifecycle.MutableLiveData
import com.antonioleiva.mymovies.ui.common.ScopedViewModel
import com.ittalent.domain.ItunesSongs
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.postException
import com.ittalent.testitandroid.ui.common.postLoading
import com.ittalent.testitandroid.ui.common.postValue
import com.ittalent.usecases.FindSongById
import com.ittalent.usecases.GetListSongsAlbumUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val trackId: Int, private val findSongById: FindSongById, private val getListSongsAlbumUseCase: GetListSongsAlbumUseCase): ScopedViewModel() {

    val model = MutableLiveData<Data<ItunesSongs>>()
    val modelListSongs = MutableLiveData<Data<ArrayList<ItunesSongs>>>()

    init {
        initScope()
    }

    fun findSong(){
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                    findSongById.invoke(trackId)
                }
            }.onSuccess { response ->
                if (response.trackId != 0){
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: "))
                }

            }.onFailure { throwable ->
                model.postException(throwable)
            }

        }
    }

    fun getListSongsAlbum(term: String){
        launch {
            modelListSongs.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                    getListSongsAlbumUseCase.invoke(term)
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()){
                    modelListSongs.postValue(response)
                } else {
                    modelListSongs.postException(Exception("${"Error"}: ${response.isEmpty().toString()}"))
                }

            }.onFailure { throwable ->
                modelListSongs.postException(throwable)
            }

        }
    }

}