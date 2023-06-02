package co.ssup.task.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import co.ssup.task.R
import co.ssup.task.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

  private var _binding: FragmentMainScreenBinding? = null
  private val binding get() = _binding!!

  private val viewModel by viewModels<MainScreenViewModel>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.textView.setOnClickListener {
      val bundle = Bundle().apply {
        putSerializable("post", viewModel.posts.value?.get(0))
      }
      view.findNavController()
        .navigate(R.id.action_mainScreenFragment_to_detailScreenFragment, bundle)
    }
    viewModel.posts.observe(viewLifecycleOwner) {
      Log.d(TAG, "onViewCreated: $it")
    }
  }
}