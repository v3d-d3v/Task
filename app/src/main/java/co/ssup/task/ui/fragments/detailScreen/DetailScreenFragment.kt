package co.ssup.task.ui.fragments.detailScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import co.ssup.task.R
import co.ssup.task.databinding.FragmentDetailScreenBinding

class DetailScreenFragment : Fragment(R.layout.fragment_detail_screen) {

  private var _binding: FragmentDetailScreenBinding? = null
  private val binding get() = _binding!!
  private val args: DetailScreenFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val post = args.post
    binding.id.text = "Id: ${post.id}"
    binding.userId.text = "UserId: ${post.userId}"
    binding.title.text = post.title
    binding.body.text = post.body
  }
}