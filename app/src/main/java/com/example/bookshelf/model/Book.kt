package com.example.bookshelf.model

import com.google.gson.annotations.SerializedName

// ----- Modelos que representan el JSON de la API de Google Books -----

// Respuesta de la búsqueda: GET volumes?q=...
// Solo se usa el id de cada resultado para luego pedir su detalle.
data class BooksSearchResponse(
    @SerializedName("items") val items: List<BookItem>? = null
)

data class BookItem(
    @SerializedName("id") val id: String
)

// Respuesta del detalle de un libro: GET volumes/{id}
data class BookDetail(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo? = null
)

data class VolumeInfo(
    @SerializedName("title") val title: String? = null,
    @SerializedName("authors") val authors: List<String>? = null,
    @SerializedName("imageLinks") val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    @SerializedName("thumbnail") val thumbnail: String? = null
)

// ----- Modelo que usa la interfaz (ya limpio y listo para mostrar) -----
data class Book(
    val id: String,
    val title: String,
    val authors: String,
    val thumbnailUrl: String
)
