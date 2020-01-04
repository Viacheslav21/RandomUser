package com.example.user.ui.main_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.user.R
import com.example.user.objects.UserInfo
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val onClick: (UserInfo) -> Unit) :
    RecyclerView.Adapter<MainObjViewHolder>() {

    private val list = mutableListOf<UserInfo>()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainObjViewHolder {
        return MainObjViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainObjViewHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    fun addItem(list: List<UserInfo>, shouldUpdate: Boolean) {
        if (shouldUpdate) {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        } else {
            this.list.addAll(list)
            this.notifyItemRangeInserted(this.list.size - list.count(), list.count())
        }
    }
}

class MainObjViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(obj: UserInfo, onClick: (UserInfo) -> Unit) {

        itemView.setOnClickListener { onClick(obj) }
        itemView.user_name.text = obj.name?.getFullName()
        val age = obj.dob?.age ?: 0

        itemView.user_age.text =
            if (age > 0) itemView.context.getString(R.string.age, age.toString()) else "unknown"

        Glide.with(itemView.context)
            .load(obj.picture?.medium)
            .apply(RequestOptions.circleCropTransform())
            .into(itemView.user_image)
    }
}