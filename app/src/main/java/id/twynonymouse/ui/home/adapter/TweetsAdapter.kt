package id.twynonymouse.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.twynonymouse.core.model.response.TweetResponse
import id.twynonymouse.core.utils.default
import id.twynonymouse.core.utils.gone
import id.twynonymouse.core.utils.loadWithCrossFade
import id.twynonymouse.core.utils.visibille
import id.twynonymouse.databinding.ItemTweetBinding
import timber.log.Timber

class TweetsAdapter(private val list: List<TweetResponse>) :
    RecyclerView.Adapter<TweetsAdapter.TweetsHolder>() {

    inner class TweetsHolder(private val binding: ItemTweetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TweetResponse, isRetweet: Boolean) = binding.apply {
            item.apply {
                setQuote(is_quote_status, quoted_status?.text.default(), quoted_status?.user?.screen_name.default(), binding)

                if (isRetweet.not()) layRetweet.gone()

                txtTweet.text = when {
                    in_reply_to_status_id_str.isNullOrBlank().not() -> {
                        setReply(text.default(), txtUserName, entities?.user_mentions)
                    }
                    else -> {
                        txtUserName.gone()
                        text
                    }
                }
                txtDate.text = created_at
                val media = extended_entities?.media
                when (media?.size.default()) {
                    0 -> hideImage()
                    1 -> {
                        showImage1(media)
                    }
                    2 -> {
                        showImage2(media)
                    }
                    3 -> {
                        showImage3(media)
                    }
                    4 -> {
                        showImage4(media)
                    }
                }
            }
        }

        private fun setQuote(
            isQuoteStatus: Boolean?,
            textQuoted: String,
            userQuoted: String,
            itemTweetBinding: ItemTweetBinding
        ) {
            if (isQuoteStatus.default()) {
                itemTweetBinding.layQuote.visibille()
                itemTweetBinding.txtTweetQuote.text = textQuoted
                itemTweetBinding.txtUserNameQuote.text = "@$userQuoted"
            } else itemTweetBinding.layQuote.gone()
        }

        private fun setReply(
            text: String,
            txtUserName: TextView,
            userMentions: List<TweetResponse.Entities.UserMention>?
        ): String {

//            binding.root.setCardBackgroundColor(
//                ContextCompat.getColor(
//                    binding.root.context,
//                    R.color.colorReply
//                )
//            )

            if (userMentions?.isEmpty().default()) return text
            return replyingOthers(text, userMentions, txtUserName)
        }

        private fun replyingOthers(
            text: String,
            userMentions: List<TweetResponse.Entities.UserMention>?,
            txtUserName: TextView
        ): String {
            val lastIndex = getLastIndex(text, 0, userMentions?.size.default())
            txtUserName.visibille()
            txtUserName.text = "Replying to ${text.substring(0, lastIndex).replace(" ", " & ")}"
            return text.substring(lastIndex + 1)
        }

        private fun getLastIndex(text: String, i: Int, size: Int): Int {
            return if (size == 0) i
            else text.indexOf(
                ' ',
                getLastIndex(
                    text, i, size - 1
                ) + 1
            )
        }

        private fun ItemTweetBinding.showImage4(media: List<TweetResponse.ExtendedEntities.Media?>?) {
            showImage3(media)
            img4.apply {
                visibille()
                loadWithCrossFade(media?.get(3)?.media_url_https)
            }
        }

        private fun ItemTweetBinding.showImage3(media: List<TweetResponse.ExtendedEntities.Media?>?) {
            showImage2(media)
            lay34img.visibille()
            img3.apply {
                visibille()
                loadWithCrossFade(media?.get(2)?.media_url_https)
            }
        }

        private fun ItemTweetBinding.showImage2(media: List<TweetResponse.ExtendedEntities.Media?>?) {
            showImage1(media)
            img2.apply {
                visibille()
                loadWithCrossFade(media?.get(1)?.media_url_https)
            }
        }

        private fun ItemTweetBinding.showImage1(media: List<TweetResponse.ExtendedEntities.Media?>?) {
            layImage.visibille()
            img1.loadWithCrossFade(media?.get(0)?.media_url_https)
        }

        private fun ItemTweetBinding.hideImage() {
            layImage.gone()
        }

        fun bindRetweet(item: TweetResponse) = binding.apply {
            layRetweet.visibille()
            txtRetweet.text = "You retweeted @${item.entities?.user_mentions?.get(0)?.screen_name}"
            item.retweeted_status?.let { bind(it, true) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsHolder {
        return TweetsHolder(
            ItemTweetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = list[position].id_str?.toLong().default()

    override fun onBindViewHolder(holder: TweetsHolder, position: Int) {
        val item = list[position]
        if (item.retweeted_status != null) holder.bindRetweet(item)
        else holder.bind(item, false)
        Timber.d("${list[position]}")
    }

}