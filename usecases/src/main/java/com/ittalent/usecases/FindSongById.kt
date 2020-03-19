package com.ittalent.usecases

import com.ittalent.data.repository.ItunesRepository
import com.ittalent.domain.ItunesSongs


class FindSongById(private val itunesRepository: ItunesRepository) {
    suspend fun invoke(id: Int): ItunesSongs = itunesRepository.findById(id)
}