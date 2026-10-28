package com.example.bookshelf.fake

import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Book

// Repositorio falso para probar el ViewModel
class FakeBooksRepository : BooksRepository {
    override suspend fun getBooks(query: String): List<Book> = FakeDataSource.books
}
