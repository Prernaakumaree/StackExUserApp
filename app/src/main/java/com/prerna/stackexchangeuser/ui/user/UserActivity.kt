package com.prerna.stackexchangeuser.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prerna.stackexchangeuser.data.model.api.User
import com.prerna.stackexchangeuser.R
import com.prerna.stackexchangeuser.ui.UserAdapter
import com.prerna.stackexchangeuser.ui.detail.UserDetailActivity
import kotlinx.android.synthetic.main.user_main.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent

class UserActivity : AppCompatActivity(), UserView, KoinComponent {

    private val presenter: UserPresenter by inject()

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_main)

        val llm = LinearLayoutManager(this)
        user_list.layoutManager = llm
        val separator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        user_list.addItemDecoration(separator)

        presenter.bind(this)
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    override fun render(state: UserScreenState) {
        when (state) {
            is UserScreenState.Loading -> showLoading()
            is UserScreenState.DataAvailable -> showUsers(state.users)
            is UserScreenState.Error -> showError(getString(R.string.load_user_error_message))
            is UserScreenState.FinishedLoading -> hideLoading()
        }
    }

    private fun showLoading() {
        error.visibility = View.GONE
        user_list.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
    }

    private fun showUsers(users: List<User>) {
        val mOnClickListener = View.OnClickListener {
            val viewHolder = it.tag as RecyclerView.ViewHolder
            val position = viewHolder.bindingAdapterPosition
            Intent(this, UserDetailActivity::class.java).apply {
                putExtra("position", position)
                startActivity(this)
            }
        }

        adapter = UserAdapter(users)
        user_list.adapter = adapter
        user_list.visibility = View.VISIBLE
        adapter.setOnClickListener(mOnClickListener)
    }

    private fun showError(message: String) {
        error.visibility = View.VISIBLE
        error.setText(message)
    }
}