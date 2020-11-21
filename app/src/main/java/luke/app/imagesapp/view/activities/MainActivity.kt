package luke.app.imagesapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import luke.app.imagesapp.R
import luke.app.imagesapp.view.fragments.ImageListFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ImageListFragment(), "LIST")

    }





}