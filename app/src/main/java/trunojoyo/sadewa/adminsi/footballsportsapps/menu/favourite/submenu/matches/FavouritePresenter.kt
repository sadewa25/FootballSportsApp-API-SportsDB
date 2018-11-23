package trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.matches

import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import trunojoyo.sadewa.adminsi.footballsportsapps.db.DatabaseOpenHelper
import trunojoyo.sadewa.adminsi.footballsportsapps.db.Favourite

class FavouritePresenter (private val favouriteView: FavouriteView,
                          private val database: DatabaseOpenHelper
) {

    fun getDataItemsFavourite(){
        favouriteView.showLoading()
        favouriteView.showDataItemsFavourite(getDatabaseUse())
        favouriteView.hideLoading()
    }

    fun getDatabaseUse():List<Favourite>{
        val data  = database.use {
            val result = select(Favourite.TABLE_FAVORITE)
            result.parseList(classParser<Favourite>())
        }
        return data
    }

}