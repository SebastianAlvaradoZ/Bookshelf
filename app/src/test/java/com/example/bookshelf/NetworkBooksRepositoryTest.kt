package com.example.bookshelf

import com.example.bookshelf.data.NetworkBooksRepository
import com.example.bookshelf.fake.FakeBooksApiService
import com.example.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NetworkBooksRepositoryTest {

    @Test
    fun networkBooksRepository_getBooks_verifyBookList() = runTest {
        val repository = NetworkBooksRepository(FakeBooksApiService())
        assertEquals(FakeDataSource.books, repository.getBooks("jazz"))
    }

    @Test
    fun networkBooksRepository_getBooks_thumbnailsUseHttps() = runTest {
        val repository = NetworkBooksRepository(FakeBooksApiService())
        assertTrue(repository.getBooks("jazz").all { it.thumbnailUrl.startsWith("https://") })
    }
}
