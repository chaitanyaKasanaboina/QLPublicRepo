package ql.com.publicrepos.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repo_list_item.view.*
import ql.com.publicrepos.R
import ql.com.publicrepos.model.RepoDetails
import ql.com.publicrepos.util.Constants
import ql.com.publicrepos.view.OnItemClickListener
import java.text.SimpleDateFormat
import java.util.*

class RepoRecyclerViewAdapter(
    private val repoList: List<RepoDetails>,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.repoName.text =
            holder.itemView.context.getString(R.string.repo_name_label, repoList[position].name)
        holder.repoLastUpdatedTime.text = holder.itemView.context.getString(
            R.string.repo_last_updated,
            repoList[position].updatedAt?.let { formatTime(it) })
        holder.repoDescription.text = holder.itemView.context.getString(
            R.string.repo_description,
            repoList[position].description
        )
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(repoList[position])
        }
    }

    private fun formatTime(time: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val desiredFormatter = SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.US)
        return desiredFormatter.format(parser.parse(time) ?: Constants.BLANK)
    }

}

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextViews that will add each repo details to
    val repoName: TextView = view.repo_name
    val repoLastUpdatedTime: TextView = view.repo_last_updated
    val repoDescription: TextView = view.repo_description
}

