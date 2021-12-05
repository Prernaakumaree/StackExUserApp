package com.prerna.stackexchangeuser.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.prerna.stackexchangeuser.R
import com.prerna.stackexchangeuser.data.model.api.User
import com.prerna.stackexchangeuser.ui.user.UserScreenState
import com.prerna.stackexchangeuser.ui.user.UserView
import com.prerna.stackexchangeuser.util.convertLongToTime
import kotlinx.android.synthetic.main.user_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent

class UserDetailActivity : AppCompatActivity(), UserView, KoinComponent {

    private val userDetailPresenter: UserDetailPresenter by inject()
    var position=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail)
        position = intent.getIntExtra("position", 1)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        userDetailPresenter.bind(this)
    }

    override fun onDestroy() {
        userDetailPresenter.unbind()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
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
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
    }

    private fun showUsers(users: List<User>) {
        val user = users[position]
        userDetailImage.load(user.profileImage) {
            crossfade(true)
            placeholder(R.drawable.ic_stack)
            transformations(CircleCropTransformation())
        }
        userName.text = getString(R.string.user_detail_name, user.displayName)
        userReputation.text = getString(R.string.user_detail_reputation, user.reputation.toString())
        topTags.text = getString(R.string.user_detail_type, user.userType)
        badges.text = getString(R.string.user_detail_badges, user.badgeCounts.gold.toString())
        location.text = getString(R.string.user_detail_location, user.location)
        creationDate.text = getString(R.string.user_detail_created_on,
            convertLongToTime(user.creationDate.toLong()))
    }

    private fun showError(message: String) {
        error.visibility = View.VISIBLE
        error.text = message
    }
}
