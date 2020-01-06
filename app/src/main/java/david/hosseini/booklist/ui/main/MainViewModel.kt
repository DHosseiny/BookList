package david.hosseini.booklist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import david.hosseini.booklist.ui.main.model.Book
import david.hosseini.booklist.ui.main.model.BookResponse

class MainViewModel(private val booksRepository: BooksRepository = BooksRepository()) :
    ViewModel() {

    private var noMoreData = false

    private val offsetLiveData = MutableLiveData(0)

    private val booksResponse: LiveData<BookResponse> = Transformations
        .switchMap(offsetLiveData) { offset ->
            booksRepository.getBooks(BOOKS_SIZE_TO_GET, offset)
        }

    val books: LiveData<List<Book>> = Transformations.map(booksResponse) {

        noMoreData = it.noMoreData
        it.booksList.booksList
    }


    fun loadMore(): Boolean {
        if (!noMoreData) {
            offsetLiveData.value = offsetLiveData.value!! + (books.value?.size ?: 0)
            return true
        }
        return false
    }
}

const val BOOKS_SIZE_TO_GET = 30