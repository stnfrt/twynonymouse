package id.twynonymouse.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import id.twynonymouse.core.model.response.TweetResponse
import id.twynonymouse.core.utils.default
import id.twynonymouse.core.utils.gone
import id.twynonymouse.core.utils.visibille
import id.twynonymouse.databinding.ItemTweetBinding

class TweetsAdapter(private val list: List<TweetResponse>) :
    RecyclerView.Adapter<TweetsAdapter.TweetsHolder>() {

    inner class TweetsHolder(private val binding: ItemTweetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TweetResponse) = binding.apply {
            item.apply {
                txtTweet.text = text
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
            root.setOnClickListener {
                Toast.makeText(this.root.context, item.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        private fun ItemTweetBinding.showImage4(media: List<TweetResponse.ExtendedEntities.Media?>?) {
            showImage3(media)
            img4.apply {
                visibille()
                load(media?.get(3)?.media_url_https)
            }
        }

        private fun ItemTweetBinding.showImage3(media: List<TweetResponse.ExtendedEntities.Media?>?) {
            showImage2(media)
            lay34img.visibille()
            img3.apply {
                visibille()
                load(media?.get(2)?.media_url_https)
            }
        }

        private fun ItemTweetBinding.showImage2(media: List<TweetResponse.ExtendedEntities.Media?>?) {
            showImage1(media)
            img2.apply {
                visibille()
                load(media?.get(1)?.media_url_https)
            }
        }

        private fun ItemTweetBinding.showImage1(media: List<TweetResponse.ExtendedEntities.Media?>?) {
            layImage.visibille()
            img1.load(media?.get(0)?.media_url_https)
        }

        private fun ItemTweetBinding.hideImage() {
            layImage.gone()
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

    override fun onBindViewHolder(holder: TweetsHolder, position: Int) {
        holder.bind(list[position])
    }

}