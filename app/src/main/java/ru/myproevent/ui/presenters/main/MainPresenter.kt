package ru.myproevent.ui.presenters.main

import android.widget.Toast
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.myproevent.ProEventApp
import ru.myproevent.domain.model.IProEventRepository
import ru.myproevent.domain.model.LoginBody
import ru.myproevent.ui.screens.IScreens
import ru.myproevent.ui.screens.Screens
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repository: IProEventRepository

    // TODO: Вынести в Dagger
    private var screens: IScreens = Screens()

    private var currActiveMenu = Menu.HOME

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.authorization())
        Thread{
            repository.getToken(LoginBody(email = "grishanin.slava@yandex.ru", password = "password"))
        }.start()
    }

    fun itemSelected(menu: Menu) {
        currActiveMenu = menu
    }

    fun openHome() {
        if (currActiveMenu == Menu.HOME) {
            return
        }
        currActiveMenu = Menu.HOME
        router.navigateTo(screens.home())
    }

    fun openContacts() {
        Toast.makeText(ProEventApp.instance, "CONTACTS", Toast.LENGTH_LONG).show()
    }

    fun openChat() {
        Toast.makeText(ProEventApp.instance, "CHAT", Toast.LENGTH_LONG).show()
    }

    fun openEvents() {
        Toast.makeText(ProEventApp.instance, "EVENTS", Toast.LENGTH_LONG).show()
    }

    fun openSettings() {
        if (currActiveMenu == Menu.SETTINGS) {
            return
        }
        currActiveMenu = Menu.SETTINGS
        router.navigateTo(screens.settings())
    }

    fun backClicked() {
        router.exit()
    }
}