package trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.teams

import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import trunojoyo.sadewa.adminsi.footballsportsapps.db.DatabaseOpenHelper
import trunojoyo.sadewa.adminsi.footballsportsapps.db.FavouriteTeamsModel

class FavouriteTeamsPresenter(private val favouriteView: FavouriteTeamsView,
                              private val database: DatabaseOpenHelper?) {

    fun getDataItemsFavourite(){
        favouriteView.showLoading()
        favouriteView.showDataItemsFavourite(getDatabaseUse())
        favouriteView.hideLoading()
    }

    fun getDatabaseUse():List<FavouriteTeamsModel>{
        val data  = database!!.use {
            val result = select(FavouriteTeamsModel.TABLE_FAVORITETEAMS)
            result.parseList(classParser<FavouriteTeamsModel>())
        }
        return data
    }

}