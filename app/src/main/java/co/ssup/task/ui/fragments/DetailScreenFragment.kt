package co.ssup.task.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import co.ssup.task.R

class DetailScreenFragment: Fragment(R.layout.fragment_detail_screen) {

  val args: DetailScreenFragmentArgs by navArgs()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val post = args.post
    Log.d("Vedant", "onViewCreated: ${post}")
  }
}