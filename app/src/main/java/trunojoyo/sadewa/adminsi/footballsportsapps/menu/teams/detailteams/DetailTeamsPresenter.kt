package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.db.DatabaseOpenHelper
import trunojoyo.sadewa.adminsi.footballsportsapps.db.FavouriteTeamsModel
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class DetailTeamsPresenter(val detailTeamsView: DetailTeamsView,
                           val apiClient: APIClient,
                           private val database: DatabaseOpenHelper,
                           val context: Context?) {

    fun getFavoriteSize(id:String){
        database.use {
            val result = select(FavouriteTeamsModel.TABLE_FAVORITETEAMS)
                .whereArgs("(idTeam = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<FavouriteTeamsModel>())
            if (!favorite.isEmpty()){
                detailTeamsView.getFavouriteSize(true)
            }else{
                detailTeamsView.getFavouriteSize(false)
            }
        }
    }

    fun removeFromFavorite(ids:String){
        try {
            database.use {
                delete(FavouriteTeamsModel.TABLE_FAVORITETEAMS, "idTeam = {userID}", "userID" to ids)
            }
        } catch (e: SQLiteConstraintException){

        }
    }
    fun addToFavorite(idTeam:String, teamLogo:String, teamName:String){
        try {
            database.use {
                insert(
                    FavouriteTeamsModel.TABLE_FAVORITETEAMS,
                    FavouriteTeamsModel.idTeam to idTeam,
                    FavouriteTeamsModel.strTeam to teamName,
                    FavouriteTeamsModel.strTeamBadge to teamLogo
                    )
            }

        } catch (e: SQLiteConstraintException){
        }
    }

    fun getInfoTeams(id:String){
        val dataItems:ArrayList<DetailTeamsModel> = arrayListOf()
        doAsync {
            val jsonObject = JSONObject(apiClient.getResponse(context?.getString(R.string.url_detail_teams)+id))
            val arrayEvents = jsonObject.getJSONArray("teams")
            for (i in 0..arrayEvents.length()-1) {
                val data = arrayEvents.getJSONObject(i)
                dataItems.add(
                    DetailTeamsModel(
                        strTeam = data.getString("strTeam"),
                        strTeamBadge = data.getString("strTeamBadge"),
                        strStadium = data.getString("strStadium"),
                        intFormedYear = data.getString("intFormedYear"),
                        strDescriptionEN = data.getString("strDescriptionEN")
                    )
                )
            }
            uiThread {
                detailTeamsView.showDataItems(dataItems)
            }
        }
    }


}