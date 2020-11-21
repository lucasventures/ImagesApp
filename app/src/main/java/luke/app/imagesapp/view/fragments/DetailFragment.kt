package luke.app.imagesapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import luke.app.imagesapp.R

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.image_view)
        val userNameTextView = view.findViewById<TextView>(R.id.username)
        val ratingTextView = view.findViewById<TextView>(R.id.rating)
        val sourceTextView = view.findViewById<TextView>(R.id.image_source)

        val url = arguments?.getString("URL")
        val username = arguments?.getString("USERNAME")
        val rating = arguments?.getString("RATING")
        val source = arguments?.getString("SOURCE")

        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(imageView)

        userNameTextView.text = username
        ratingTextView.text = rating
        sourceTextView.text = source
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}