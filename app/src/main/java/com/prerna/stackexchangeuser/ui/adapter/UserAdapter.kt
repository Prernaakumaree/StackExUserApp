package com.prerna.stackexchangeuser.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prerna.stackexchangeuser.data.model.api.User
import com.prerna.stackexchangeuser.R
import kotlinx.android.synthetic.main.user_row_item.view.*

class UserAdapter(
    private val data: List<User>
) : RecyclerView.Adapter<UserViewHolder>() {

    companion object {
        var mOnClickListener: View.OnClickListener? = null
    }

    fun setOnClickListener(onClickListener: View.OnClickListener?) {
        mOnClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.user_row_item, parent, false)

        return UserViewHolder(view, mOnClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }
}

class UserViewHolder(private val view: View,
                     onClickListener: View.OnClickListener?) : RecyclerView.ViewHolder(view) {
    init {
        view.root.tag = this
        view.root.setOnClickListener(onClickListener)
    }
    fun bind(item: User) {
        view.userReputation.text = item.reputation.toString()
        view.userName.text = item.displayName
    }

}