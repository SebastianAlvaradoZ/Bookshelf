package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BooksApiService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

// Repositorio: separa la capa de UI de la capa de datos.
// La UI solo conoce esta interfaz, así en las pruebas se puede usar una versión falsa.
interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
}

// Implementación que obtiene los libros desde la red
class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

    override suspend fun getBooks(query: String): List<Book> = coroutineScope {
        // 1) Una petición de búsqueda para obtener los ids de los libros
        val ids = booksApiService.searchBooks(query).items.orEmpty().map { it.id }

        // 2) Una petición por cada libro para obtener su detalle (en paralelo).
        //    Si el detalle de un libro falla, se omite ese libro en lugar de fallar todo.
        ids.map { id ->
            async { runCatching { booksApiService.getBook(id) }.getOrNull() }
        }.awaitAll()
            .filterNotNull()
            .mapNotNull { detalle ->
                val info = detalle.volumeInfo
                val thumbnail = info?.imageLinks?.thumbnail ?: return@mapNotNull null
                Book(
                    id = detalle.id,
                    title = info.title ?: "Sin título",
                    authors = info.authors?.joinToString(", ") ?: "Autor desconocido",
                    // Google devuelve las imágenes con http://; se cambian a https://
                    thumbnailUrl = thumbnail.replaceFirst("http://", "https://")
                )
            }
    }
}
