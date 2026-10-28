package com.example.bookshelf.fake

import com.example.bookshelf.model.BookDetail
import com.example.bookshelf.model.BooksSearchResponse
import com.example.bookshelf.network.BooksApiService

// Servicio falso: reemplaza a Retrofit en las pruebas y no hace llamadas de red
class FakeBooksApiService : BooksApiService {
    override suspend fun searchBooks(query: String, maxResults: Int): BooksSearchResponse =
        FakeDataSource.searchResponse

    override suspend fun getBook(id: String): BookDetail =
        FakeDataSource.details.getValue(id)
}
