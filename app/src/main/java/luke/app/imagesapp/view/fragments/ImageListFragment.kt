package luke.app.imagesapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import luke.app.imagesapp.R
import luke.app.imagesapp.model.giphy.model.Data
import luke.app.imagesapp.repository.GiphyRepository
import luke.app.imagesapp.view.activities.MainActivity
import luke.app.imagesapp.view.adapter.ImagesRecyclerAdapter
import luke.app.imagesapp.viewmodel.GiphyViewModel


private lateinit var giphyViewModel: GiphyViewModel

private var recyclerView: RecyclerView? = null
private var adapter: ImagesRecyclerAdapter? = null
private var items = ArrayList<Data>()

class ImageListFragment : Fragment() {

    companion object {
        fun newInstance() = ImageListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //todo: add search view
        giphyViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!).create(
                GiphyViewModel::class.java
            )

        //required to make calls
        giphyViewModel.setRepository(GiphyRepository())

        //trending observer
        giphyViewModel.trendingImagesLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("GIPHY TRENDING", it.toString())
            if (recyclerView == null) {
                recyclerView = view.findViewById(R.id.recycler_view)
                recyclerView!!.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                recyclerView!!.adapter = ImagesRecyclerAdapter(activity as MainActivity, it.data)
                recyclerView!!.adapter!!.notifyDataSetChanged()
            } else {
                recyclerView!!.adapter = ImagesRecyclerAdapter(activity as MainActivity, it.data)
            }
        })

        //query observer
        giphyViewModel.queryLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("GIPHY QUERY", it.toString())
            if (recyclerView == null) {
                recyclerView = view.findViewById(R.id.recycler_view)
                recyclerView!!.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                items = it.data
                adapter = ImagesRecyclerAdapter(activity as MainActivity, items)
                recyclerView!!.adapter = adapter
            } else {
                items.clear()
                items = it.data
                adapter!!.notifyDataSetChanged()
            }
        })

        giphyViewModel.getTrendingImages()
    }


}