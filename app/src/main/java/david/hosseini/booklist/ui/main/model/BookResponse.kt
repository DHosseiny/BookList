package david.hosseini.booklist.ui.main.model

data class BookResponse(
    val booksList: BooksList,
    val noMoreData: Boolean,
    val title: String
)

data class BooksList(
    val booksList: List<Book>,
    val currentSpinnerPosition: Int
)