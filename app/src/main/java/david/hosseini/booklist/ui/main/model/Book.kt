package david.hosseini.booklist.ui.main.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id: Int,
    val title: String,
    val sourceBookId: Int,
    val description: String,
    val publisherID: Int,
    val price: Int,
    val numberOfPages: Int,
    val sticker: String,
    val beforeOffPrice: Int,
    val offText: String,
    val priceColor: String,
    val isRtl: Boolean,
    val showOverlay: Boolean,
    val physicalPrice: Int,
    val publishDate: String,
    val destination: Int,
    val type: String,
    val coverUri: String,
    val shareUri: String,
    val shareText: String,
    val publisher: String,
    val rating: Float,
    @SerializedName("authors")
    private val authorsList: List<Authors>,
    val subscriptionAvailable: Boolean,
    val newsItemCreationDate: String,
    val state: Int,
    val encrypted: Boolean,
    val recommendationCounter: RecommendationCounter,
    val currencyPrice: Float,
    val currencyBeforeOffPrice: Float
) {

    fun getAuthors(): String =
        authorsList.joinToString(separator = " | ", limit = 2) { it.firstName + " " + it.lastName }
}


data class RecommendationCounter(
    val total: Int,
    val recommenderCount: Int
)

data class Authors(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val type: Int
)
