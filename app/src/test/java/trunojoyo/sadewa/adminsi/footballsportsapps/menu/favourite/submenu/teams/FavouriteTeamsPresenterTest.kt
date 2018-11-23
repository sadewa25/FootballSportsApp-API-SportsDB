package trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.teams

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import trunojoyo.sadewa.adminsi.footballsportsapps.db.DatabaseOpenHelper
import trunojoyo.sadewa.adminsi.footballsportsapps.db.FavouriteTeamsModel
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.matches.FavouritePresenter

class FavouriteTeamsPresenterTest {

    @Mock
    private  lateinit var presenter:FavouriteTeamsPresenter

    @Mock
    private lateinit var view: FavouriteTeamsView

    @Mock
    private lateinit var databaseOpenHelper: DatabaseOpenHelper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = FavouriteTeamsPresenter(view,databaseOpenHelper)
    }

    @Test
    fun getDataItemsFavourite() {
        var response:List<FavouriteTeamsModel> = arrayListOf()
        Mockito.`when`(presenter.getDatabaseUse())
            .thenReturn(response)
        view.showLoading()
        view.showDataItemsFavourite(response)
        view.hideLoading()
    }
}