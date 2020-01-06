package david.hosseini.booklist.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import david.hosseini.booklist.ui.main.model.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository(private val booksService: BooksService = HttpManager.booksService) {


    fun getBooks(size: Int, start: Int): LiveData<BookResponse> {

        val booksResponse = MutableLiveData<BookResponse>()

        booksService.getBooks(size, start).enqueue(object : Callback<BookResponse> {
            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                Log.w("BooksRepository", "Error loading books!")
            }

            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                if (response.isSuccessful) {

                    booksResponse.value = response.body()
                } else Log.w("BooksRepository", "Error loading books!")
            }
        })

        return booksResponse
    }


}
