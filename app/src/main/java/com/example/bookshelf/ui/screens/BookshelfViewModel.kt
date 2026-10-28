package com.example.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

// Estados posibles de la pantalla
sealed interface BookshelfUiState {
    data class Success(val books: List<Book>) : BookshelfUiState
    object Error : BookshelfUiState
    object Loading : BookshelfUiState
}

// ViewModel: guarda el estado de la pantalla y pide los datos al repositorio.
// Sobrevive a los cambios de configuración (por ejemplo, rotar el celular).
class BookshelfViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    var uiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    // Término de búsqueda actual (el codelab usa "jazz history" como ejemplo)
    var query: String by mutableStateOf("jazz history")
        private set

    init {
        getBooks()
    }

    fun updateQuery(nuevo: String) {
        query = nuevo
    }

    fun getBooks() {
        val termino = query.trim()
        if (termino.isEmpty()) return
        viewModelScope.launch {
            uiState = BookshelfUiState.Loading
            uiState = try {
                BookshelfUiState.Success(booksRepository.getBooks(termino))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                BookshelfUiState.Error
            }
        }
    }

    // Fábrica: obtiene el repositorio del contenedor de la aplicación (inyección de dependencias)
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as BookshelfApplication
                BookshelfViewModel(application.container.booksRepository)
            }
        }
    }
}
