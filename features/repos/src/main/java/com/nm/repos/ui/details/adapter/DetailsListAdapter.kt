package com.nm.repos.ui.details.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.nm.data.entity.Detail
import com.nm.repos.R

class DetailsListAdapter(
    private val context: Context,
    private val resource: Int,
    private val data: List<Detail>,
    private val mGlide: RequestManager,
    private val itemListener: (Detail) -> Unit
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
        var photo: ImageView = itemView.findViewById(R.id.img_go_to_user)
        var title: TextView = itemView.findViewById(R.id.txt_title_pull_request)
        var body: TextView = itemView.findViewById(R.id.txt_subtitle_pull_request)
        var username: TextView = itemView.findViewById(R.id.txt_username)
        var lastname: TextView = itemView.findViewById(R.id.txt_lastname)

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

        mGlide.load(item.user.avatar_url)
            .placeholder(R.drawable.ic_username)
            .into(defaultVH.photo)

        defaultVH.title.text = item.title
        defaultVH.body.text = item.body
        defaultVH.username.text = item.user.login
        defaultVH.lastname.text = item.user.type
    }
}