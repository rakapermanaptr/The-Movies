package com.example.themovies.presentation.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.domain.entities.Cast
import com.example.themovies.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_caster.view.*

class CasterAdapter(private val onItemClick: (cast: Cast) -> Unit) :
    RecyclerView.Adapter<CasterAdapter.ViewHolder>() {

    private val casterList = mutableListOf<Cast>()

    fun addItems(casters: List<Cast>) {
        casterList.addAll(casters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_caster, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = casterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val caster = casterList[position]
        holder.bind(caster)
        holder.itemView.setOnClickListener { onItemClick(caster) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cast: Cast) {
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + cast.profilePath)
                .into(itemView.img_caster)

            itemView.tv_name_caster.text = cast.name
        }
    }

}