package com.ittalent.usecases

import com.ittalent.data.repository.ItunesRepository
import com.ittalent.domain.ItunesSongs

class GetListSongsUseCase(private val itunesRepository: ItunesRepository) {

    suspend fun invoke(song: String, offset: Int): ArrayList<ItunesSongs> = itunesRepository.getListSongs(song, offset)

}