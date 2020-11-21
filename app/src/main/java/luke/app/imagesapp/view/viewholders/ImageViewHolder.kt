package luke.app.imagesapp.view.viewholders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import luke.app.imagesapp.R

class ImageViewHolder(v: View) : RecyclerView.ViewHolder(v){
    val imageView : ImageView = v.findViewById(R.id.image_view)
}