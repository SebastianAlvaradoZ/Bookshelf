package com.example.bookshelf.network

import com.example.bookshelf.model.BookDetail
import com.example.bookshelf.model.BooksSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Endpoints de la API de Google Books (URL base: https://www.googleapis.com/books/v1/)
interface BooksApiService {

    // GET volumes?q=jazz+history&maxResults=20 -> busca libros por término
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20
    ): BooksSearchResponse

    // GET volumes/{id} -> información completa de un libro (incluye la portada)
    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): BookDetail
}
