package ru.myproevent.ui.presenters.home

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.myproevent.ui.screens.IScreens
import ru.myproevent.ui.screens.Screens
import javax.inject.Inject

class HomePresenter : MvpPresenter<HomeView>() {
    @Inject
    lateinit var router: Router

    // TODO: вынести в Dagger
    private var screens: IScreens = Screens()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}