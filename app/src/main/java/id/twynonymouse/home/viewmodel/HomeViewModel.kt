package id.twynonymouse.home.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homePresenter: HomePresenter
) : RainbowCakeViewModel<HomeViewState>(
    Initial
) {

    fun load() = execute {
        try {
            viewState = Loading
            viewState =
                UserReady(homePresenter.getData())
        } catch (e: Exception) {
            viewState = Error(e.message)
        }
    }
}
