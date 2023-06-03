package co.ssup.task.ui.fragments.mainScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.ssup.task.R
import co.ssup.task.data.models.Post

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

  inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  private val differCallback = object : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
      return oldItem == newItem
    }
  }

  val differ = AsyncListDiffer(this, differCallback)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
    return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
  }

  override fun getItemCount(): Int {
    return differ.currentList.size
  }

  private var onItemClickListener: ((Post) -> Unit)? = null

  override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
    val post = differ.currentList[position]
    holder.itemView.apply {
      findViewById<TextView>(R.id.tvTitle).text = post.title
      setOnClickListener {
        onItemClickListener?.let { it(post) }
      }
    }
  }

  fun setOnItemClickListener(listener: (Post) -> Unit) {
    onItemClickListener = listener
  }
}