package com.ittalent.usecases

import com.ittalent.data.repository.ItunesRepository
import com.ittalent.domain.ItunesSongs

class GetListSongsAlbumUseCase(private val itunesRepository: ItunesRepository) {

    suspend fun invoke(term: String): ArrayList<ItunesSongs> = itunesRepository.getListSongsAlbum(term)

}