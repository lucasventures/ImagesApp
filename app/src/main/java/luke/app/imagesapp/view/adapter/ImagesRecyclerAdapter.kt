package luke.app.imagesapp.view.adapter

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import luke.app.imagesapp.R
import luke.app.imagesapp.model.giphy.model.Data
import luke.app.imagesapp.view.activities.MainActivity
import luke.app.imagesapp.view.fragments.DetailFragment
import luke.app.imagesapp.view.viewholders.ImageViewHolder


class ImagesRecyclerAdapter(
    private var activity: MainActivity,
    private var giphyData: ArrayList<Data>
) :
    RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(View.inflate(parent.context, R.layout.view_holder_images, null))
    }

    override fun getItemCount(): Int {
        return giphyData.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val giphy = giphyData[position]
        val imageView = holder.imageView

        if (giphy.images.original.url.isNotEmpty()) {
            Glide.with(imageView.context)
                .load(giphy.images.original.url)
                .centerCrop()
                .into(imageView)
        }

        holder.imageView.setOnClickListener {
            //transact to next fragment

            val detailFragment = DetailFragment.newInstance()
            val bundle = Bundle()

            bundle.putString("USERNAME", giphy.username)
            bundle.putString("RATING", giphy.rating)
            bundle.putString("SOURCE", giphy.source)
            bundle.putString("URL", giphy.url)

            detailFragment.arguments

            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment, "DETAIL")
        }
    }
}