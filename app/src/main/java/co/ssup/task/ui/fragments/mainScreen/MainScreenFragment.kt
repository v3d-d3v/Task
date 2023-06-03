package co.ssup.task.ui.fragments.mainScreen

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.ssup.task.R
import co.ssup.task.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

  private var _binding: FragmentMainScreenBinding? = null
  private val binding get() = _binding!!

  private val viewModel by viewModels<MainScreenViewModel>()
  lateinit var postAdapter: PostAdapter

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
    setupRecyclerView()
    postAdapter.setOnItemClickListener {
      val bundle = Bundle().apply {
        putSerializable("post", it)
      }
      view.findNavController()
        .navigate(R.id.action_mainScreenFragment_to_detailScreenFragment, bundle)
    }
    viewModel.posts.observe(viewLifecycleOwner) {
      postAdapter.differ.submitList(it)
    }
  }

  private fun setupRecyclerView() {
    postAdapter = PostAdapter()
    binding.rvRecyclerView.apply {
      adapter = postAdapter
      layoutManager = LinearLayoutManager(activity)
    }
  }
}