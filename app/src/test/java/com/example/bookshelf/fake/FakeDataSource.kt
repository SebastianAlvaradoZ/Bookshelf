package com.example.bookshelf.fake

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.BookDetail
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BooksSearchResponse
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo

// Datos falsos para las pruebas (no se usa la red)
object FakeDataSource {

    val searchResponse = BooksSearchResponse(
        items = listOf(BookItem("A1"), BookItem("B2"), BookItem("C3"))
    )

    val details = mapOf(
        "A1" to BookDetail(
            "A1",
            VolumeInfo("Historia del Jazz", listOf("Autor Uno"), ImageLinks("http://books.google.com/a1.jpg"))
        ),
        "B2" to BookDetail(
            "B2",
            VolumeInfo("Kotlin para Android", listOf("Autor Dos", "Autor Tres"), ImageLinks("http://books.google.com/b2.jpg"))
        ),
        // Libro sin portada: el repositorio debe omitirlo
        "C3" to BookDetail("C3", VolumeInfo("Libro sin imagen", null, null))
    )

    // Resultado esperado después de procesar los datos en el repositorio
    val books = listOf(
        Book("A1", "Historia del Jazz", "Autor Uno", "https://books.google.com/a1.jpg"),
        Book("B2", "Kotlin para Android", "Autor Dos, Autor Tres", "https://books.google.com/b2.jpg")
    )
}
