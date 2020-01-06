package david.hosseini.booklist.ui.main

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import david.hosseini.booklist.ui.main.model.Book
import kotlinx.android.synthetic.main.item_book.view.*


class BooksAdapter : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    private val books = mutableListOf<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(david.hosseini.booklist.R.layout.item_book, parent, false)

        return BookViewHolder(view)
    }

    override fun getItemCount(): Int = books.size


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        holder.bind(books[position])
    }

    fun addItems(books: List<Book>) {
        val beforeAddSize = this.books.size
        this.books.addAll(books)
        notifyItemRangeInserted(beforeAddSize, books.size)
    }


    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: Book) {

            loadImage(book.coverUri, itemView.image_book)

            itemView.text_book_title.text = book.title
            itemView.text_book_author.text = book.getAuthors()
            itemView.text_book_publisher.text = book.publisher

            itemView.ratingBar_book.rating = book.rating
            itemView.text_book_price.text = getPriceText(book.price)
            if (book.beforeOffPrice != 0) {
                showBeforeOffPriceText(book.beforeOffPrice)
                loadImage(book.sticker, itemView.image_book_off)
            } else {
                itemView.text_book_before_off_price.text = ""
                Glide.with(itemView)
                    .clear(itemView.image_book_off)
            }
        }

        private fun showBeforeOffPriceText(beforeOffPrice: Int) {
            itemView.text_book_before_off_price.let {
                it.text = getPriceText(beforeOffPrice)
                it.paintFlags = it.paintFlags or STRIKE_THRU_TEXT_FLAG
            }
        }

        private fun getPriceText(price: Int) =
            itemView.context.getString(david.hosseini.booklist.R.string.toman, price)

        private fun loadImage(coverUri: String, imageView: ImageView) {
            Glide
                .with(itemView)
                .load(coverUri)
                .into(imageView)
        }
    }

}
