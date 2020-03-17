package com.ittalent.usecases

import com.ittalent.data.repository.ItunesRepository
import com.ittalent.domain.ItunesSongs

class GetListSongsUseCase(private val itunesRepository: ItunesRepository) {

    suspend fun invoke(song: String): List<ItunesSongs> = itunesRepository.getListSongs(song)

}