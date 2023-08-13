package com.rikkimikki.telegramgallery.domain.entity

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoItem(
    override val msgId: Long,
    override val mediaId: Long,
    override val groupId: Long,
    override val date: Long,
    override val thumbnailId: Long,
    override val name: String?,
    override val tags: List<String>,
    override val isLiked: Boolean,
    override val size: Long,
    override val resolution: Pair<Int,Int>,
    val duration: Long,
) : FeedItem()

@Parcelize
data class PhotoItem(
    override val msgId: Long,
    override val mediaId: Long,
    override val groupId: Long,
    override val date: Long,
    override val thumbnailId: Long,
    override val name: String?,
    override val tags: List<String>,
    override val isLiked: Boolean,
    override val size: Long,
    override val resolution: Pair<Int,Int>,
) : FeedItem()


@Parcelize
sealed class FeedItem : Parcelable {
    abstract val msgId: Long
    abstract val mediaId: Long
    abstract val groupId: Long
    abstract val date: Long
    abstract val thumbnailId: Long
    abstract val name: String?
    abstract val tags: List<String>
    abstract val isLiked: Boolean
    abstract val size: Long
    abstract val resolution: Pair<Int,Int>

    companion object {

        val NavigationType: NavType<FeedItem> = object : NavType<FeedItem>(false) {

            override fun get(bundle: Bundle, key: String): FeedItem? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FeedItem {
                return Gson().fromJson(value, FeedItem::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: FeedItem) {
                bundle.putParcelable(key, value)
            }
        }
    }
}

/*@Parcelize
data class FeedItem(
    val msgId: Long,
    val mediaId: Long,
    val groupId: Long,
    val date: Long,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    val statistics: List<Int>,
    val isLiked: Boolean
) : Parcelable {

    inner class VideoItem{

    }
    companion object {

        val NavigationType: NavType<FeedItem> = object : NavType<FeedItem>(false) {

            override fun get(bundle: Bundle, key: String): FeedItem? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FeedItem {
                return Gson().fromJson(value, FeedItem::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: FeedItem) {
                bundle.putParcelable(key, value)
            }
        }
    }
}
*/