package com.nm.repos.ui.repos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.nm.data.entity.Repo
import com.nm.repos.R

class RepoListAdapter(
    private val context: Context,
    private val resource: Int,
    private val data: List<Repo>,
    private val mGlide: RequestManager,
    private val itemListener: (Repo) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewModel: View

        viewModel = inflater.inflate(resource, parent, false)
        return DefaultVH(viewModel)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        processDefault(holder, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private inner class DefaultVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.txt_id)
        var photo: ImageView = itemView.findViewById(R.id.img_go_to_user)
        var title: TextView = itemView.findViewById(R.id.txt_title)
        var subtitle: TextView = itemView.findViewById(R.id.txt_subtitle)
        var username: TextView = itemView.findViewById(R.id.txt_username)
        var lastname: TextView = itemView.findViewById(R.id.txt_lastname)
        var forksNumber: TextView = itemView.findViewById(R.id.txt_number_fork)
        var starNumber: TextView = itemView.findViewById(R.id.txt_number_star)


        init {
            itemView.rootView.setOnClickListener {
                val position = adapterPosition
                val item = data[position]
                itemListener.invoke(item)
            }
        }
    }

    private fun processDefault(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        val defaultVH = holder as DefaultVH

        mGlide.load(item.owner.avatar_url)
            .placeholder(R.drawable.ic_username)
            .into(defaultVH.photo)

        defaultVH.id.text = item.id.toString()
        defaultVH.title.text = item.name
        defaultVH.subtitle.text = item.description
        defaultVH.username.text = item.owner.login
        defaultVH.lastname.text = item.owner.type
        defaultVH.forksNumber.text = item.forks_count.toString()
        defaultVH.starNumber.text = item.stargazers_count.toString()
    }
}