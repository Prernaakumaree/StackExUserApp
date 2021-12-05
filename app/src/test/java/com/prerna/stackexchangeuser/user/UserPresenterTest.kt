package com.prerna.stackexchangeuser.user

import com.josycom.mayorjay.flowoverstack.network.UserResponse
import com.prerna.stackexchangeuser.ui.user.UserPresenter
import com.prerna.stackexchangeuser.ui.user.UserScreenState
import com.prerna.stackexchangeuser.ui.user.UserView
import com.prerna.stackexchangeuser.rules.RxSchedulerRule
import com.prerna.stackexchangeuser.ui.usecase.UserUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifyOrder
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserPresenterTest {

    @get:Rule
    val rxRule = RxSchedulerRule()

    @MockK lateinit var userUseCase: UserUseCase
    @RelaxedMockK lateinit var view: UserView

    private val anyUser = UserResponse()

    private val userPresenter by lazy {
        UserPresenter(userUseCase)
    }

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun `binding loads users`() {
        every { userUseCase.execute() } returns Single.just(anyUser)

        userPresenter.bind(view)

        verifyOrder {
            view.render(any<UserScreenState.Loading>())
            view.render(any<UserScreenState.DataAvailable>())
            view.render(any<UserScreenState.FinishedLoading>())
        }
    }

    @Test
    fun `error on binding shows error state after loading`() {
        every { userUseCase.execute() } returns Single.error(Throwable())

        userPresenter.bind(view)

        verifyOrder {
            view.render(any<UserScreenState.Loading>())
            view.render(any<UserScreenState.Error>())
            view.render(any<UserScreenState.FinishedLoading>())
        }
    }
}