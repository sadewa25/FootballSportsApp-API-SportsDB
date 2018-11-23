package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.detail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import trunojoyo.sadewa.adminsi.footballsportsapps.CoroutineContextProvider
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.db.DatabaseOpenHelper
import trunojoyo.sadewa.adminsi.footballsportsapps.db.Favourite
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class DetailPresenter(private val detailView: DetailView,
                      private val database: DatabaseOpenHelper,
                      private val context: Context,
                      private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getFavoriteSize(id:String){
        detailView.showLoading()
        database.use {
            val result = select(Favourite.TABLE_FAVORITE)
                .whereArgs("(TEAM_ID_EVENT = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<Favourite>())
            if (!favorite.isEmpty()){
                detailView.getFavouriteSize(true)
            }else{
                detailView.getFavouriteSize(false)
            }
        }
        detailView.hideLoading()
    }

    fun showImgDataItems(idHome:String,idAway:String,apiClient: APIClient){
        doAsync {
            val jsonObjectHome = JSONObject(apiClient.getResponse("${context.getString(R.string.url_detail_img_match)}?id=${idHome}"))
            val dataArrayHome = jsonObjectHome.getJSONArray("teams")
            val dataHome = dataArrayHome.getJSONObject(0)

            val jsonObjectAway = JSONObject(apiClient.getResponse("${context.getString(R.string.url_detail_img_match)}?id=${idAway}"))
            val dataArrayAway = jsonObjectAway.getJSONArray("teams")
            val dataAway = dataArrayAway.getJSONObject(0)

            uiThread {
                detailView.setImgItemsBadges(dataHome.getString("strTeamBadge"),dataAway.getString("strTeamBadge"))
            }
        }
    }

    fun removeFromFavorite(ids:String){
        detailView.showLoading()
        try {
            database.use {
                delete(Favourite.TABLE_FAVORITE, "TEAM_ID_EVENT = {userID}", "userID" to ids)
            }
            detailView.hideLoading()
        } catch (e: SQLiteConstraintException){
            detailView.hideLoading()
        }
    }
    fun addToFavorite(id_event:String,
                      awayTeam:String,
                      homeTeam:String,
                      dateEvent:String,
                      scoreHome:String,
                      scoreAway:String,
                      paramsID:String){
        detailView.showLoading()
        try {
            database.use {
                insert(
                    Favourite.TABLE_FAVORITE,
                    Favourite.TEAM_ID_EVENT to id_event,
                    Favourite.TEAM_AWAY to awayTeam,
                    Favourite.TEAM_HOME to homeTeam,
                    Favourite.TEAM_DATE_EVENT to dateEvent,
                    Favourite.TEAM_SCORE_HOME to scoreHome,
                    Favourite.TEAM_SCORE_AWAY to scoreAway,
                    Favourite.TEAM_PARAMETER_ID to paramsID)
            }
            detailView.hideLoading()
        } catch (e: SQLiteConstraintException){
            detailView.hideLoading()
        }
    }

    fun showDetailDataItems(id:String, apiClient: APIClient, param:String){
        detailView.showLoading()
        async(contextPool.main){
            val data = bg{
                returnDataModel(apiClient, id)
            }
            detailView.setDataItems(data.await(),param)
            showImgDataItems(data.await().idHomeTeam,data.await().idAwayTeam,apiClient)
            detailView.hideLoading()
        }
    }

    fun returnDataModel(apiClient: APIClient, id:String):DataModel{
        val jsonObject = JSONObject(apiClient.getResponse("${context.getString(R.string.url_detail_data_match)}?id=${id}"))
        val arrayEvents = jsonObject.getJSONArray("events")
        val data = arrayEvents.getJSONObject(0)
        return DataModel(
            data.getString("idEvent"),
            data.getString(context.getString(R.string.json_dateEvent)),
            data.getString(context.getString(R.string.json_strHomeTeam)),
            data.getString(context.getString(R.string.json_strAwayTeam)),
            data.getString(context.getString(R.string.json_intHomeScore)),
            data.getString(context.getString(R.string.json_intAwayScore)),
            data.getString(context.getString(R.string.json_strHomeGoalDetails)),
            data.getString(context.getString(R.string.json_strHomeLineupGoalkeeper)),
            data.getString(context.getString(R.string.json_strHomeLineupDefense)),
            data.getString(context.getString(R.string.json_strHomeLineupMidfield)),
            data.getString(context.getString(R.string.json_strHomeLineupForward)),
            data.getString(context.getString(R.string.json_strHomeLineupSubstitutes)),
            data.getString(context.getString(R.string.json_strAwayGoalDetails)),
            data.getString(context.getString(R.string.json_strAwayLineupGoalkeeper)),
            data.getString(context.getString(R.string.json_strAwayLineupDefense)),
            data.getString(context.getString(R.string.json_strAwayLineupMidfield)),
            data.getString(context.getString(R.string.json_strAwayLineupForward)),
            data.getString(context.getString(R.string.json_strAwayLineupSubstitutes)),
            data.getString(context.getString(R.string.json_intHomeShots)),
            data.getString(context.getString(R.string.json_intAwayShots)),
            data.getString(context.getString(R.string.json_idhometeam)),
            data.getString(context.getString(R.string.json_idawayteam))
        )
    }


}