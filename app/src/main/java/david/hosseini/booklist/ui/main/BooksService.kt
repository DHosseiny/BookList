package david.hosseini.booklist.ui.main

import david.hosseini.booklist.ui.main.model.BookResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object HttpManager {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val booksService: BooksService = retrofit.create(BooksService::class.java)
}

interface BooksService {

    @GET("everything?filters={\"list\":[{\"id\":-169,\"type\":1},{\"type\":13,\"id\":0}]}&order=5")
    fun getBooks(
        @Query("size") size: Int,
        @Query("start") start: Int
    ): Call<BookResponse>
}

const val BASE_URL = "https://get.taaghchecdn.com/v1/"