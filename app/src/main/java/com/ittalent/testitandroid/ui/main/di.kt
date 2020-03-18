package com.ittalent.testitandroid.ui.main

import com.ittalent.data.repository.ItunesRepository
import com.ittalent.usecases.GetListSongsUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainActivityModule() {

    @Provides
    fun mainViewModelProvider(getListSongsUseCase: GetListSongsUseCase) = MainViewModel(getListSongsUseCase)

    @Provides
    fun getListSongsUseCaseProvider(itunesRepository: ItunesRepository) = GetListSongsUseCase(itunesRepository)

}

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    val mainViewModel: MainViewModel
}
