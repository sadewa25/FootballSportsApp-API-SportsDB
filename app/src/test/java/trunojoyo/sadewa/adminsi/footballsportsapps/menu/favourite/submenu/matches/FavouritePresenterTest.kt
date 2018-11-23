package trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.matches

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import trunojoyo.sadewa.adminsi.footballsportsapps.db.DatabaseOpenHelper
import trunojoyo.sadewa.adminsi.footballsportsapps.db.Favourite

class FavouritePresenterTest {

    @Mock
    private  lateinit var presenter:FavouritePresenter

    @Mock
    private lateinit var view: FavouriteView

    @Mock
    private lateinit var databaseOpenHelper: DatabaseOpenHelper

    @Test
    fun getDataItemsFavourite() {
        var response:List<Favourite> = arrayListOf()
        Mockito.`when`(presenter.getDatabaseUse())
            .thenReturn(response)
        view.showLoading()
        view.showDataItemsFavourite(response)
        view.hideLoading()
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = FavouritePresenter(view,databaseOpenHelper)
    }


}