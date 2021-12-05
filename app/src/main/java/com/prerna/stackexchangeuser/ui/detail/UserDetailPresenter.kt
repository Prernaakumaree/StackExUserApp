package com.prerna.stackexchangeuser.ui.detail

import android.util.Log
import com.prerna.stackexchangeuser.ui.usecase.UserUseCase
import com.prerna.stackexchangeuser.ui.user.UserScreenState
import com.prerna.stackexchangeuser.ui.user.UserView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

class UserDetailPresenter(
    private val userUseCase: UserUseCase,
) : KoinComponent {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: UserView

    fun bind(view: UserView) {
        this.view = view
        compositeDisposable.add(loadUsers())
    }

    fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun loadUsers() = userUseCase.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.render(UserScreenState.Loading) }
        .doAfterTerminate { view.render(UserScreenState.FinishedLoading) }
        .subscribe(
            {
                Log.e("zzz", "User response >> ${it.items}")
                view.render(UserScreenState.DataAvailable(it.items))
            },
            { view.render(UserScreenState.Error(it)) }
        )
}
