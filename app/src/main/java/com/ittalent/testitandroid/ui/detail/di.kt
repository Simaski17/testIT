package com.ittalent.testitandroid.ui.detail

import com.ittalent.data.repository.ItunesRepository
import com.ittalent.usecases.FindSongById
import com.ittalent.usecases.GetListSongsAlbumUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailActivityModule(private val trackId: Int) {

    @Provides
    fun detailViewModelProvider(findSongById: FindSongById, getListSongsAlbumUseCase: GetListSongsAlbumUseCase): DetailViewModel {
        return DetailViewModel(trackId, findSongById, getListSongsAlbumUseCase) }

    @Provides
    fun findSongById(itunesRepository: ItunesRepository) = FindSongById(itunesRepository)

    @Provides
    fun getListSongsAlbumUseCaseProvider(itunesRepository: ItunesRepository) = GetListSongsAlbumUseCase(itunesRepository)

}

@Subcomponent(modules = [DetailActivityModule::class])
interface DetailActivityComponent {
    val detailViewModel: DetailViewModel
}