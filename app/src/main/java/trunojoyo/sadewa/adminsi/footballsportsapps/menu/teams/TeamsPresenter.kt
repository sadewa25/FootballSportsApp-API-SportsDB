package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams

import android.content.Context
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.detail.DataModel
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchModel
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class TeamsPresenter(val nextMatchView: TeamsView,
                     val apiClient: APIClient,
                     val context: Context?) {

    fun getDataLeague(url: String){
        val dataItems:ArrayList<MatchModel> = arrayListOf()
        doAsync {
            val jsonObject = JSONObject(apiClient.getResponse(url))
            val arrayEvents = jsonObject.getJSONArray("countrys")
            for (i in 0..arrayEvents.length()-1) {
                val data = arrayEvents.getJSONObject(i)
                dataItems.add(
                    MatchModel(
                        idLeague = data.getString("idLeague"),
                        strLeague = data.getString("strLeague")
                    )
                )
            }
            uiThread {
                nextMatchView.showDataLeague(dataItems)
            }
        }
    }

    fun getDataTeams(url: String){
        nextMatchView.showLoading()
        val dataItems:ArrayList<DataModel> = arrayListOf()
        doAsync {
            val jsonObject = JSONObject(apiClient.getResponse(url))
            val arrayEvents = jsonObject.getJSONArray("teams")
            for (i in 0..arrayEvents.length()-1) {
                val data = arrayEvents.getJSONObject(i)
                dataItems.add(
                    DataModel(
                        strTeam = data.getString("strTeam"),
                        strTeamBadge = data.getString("strTeamBadge"),
                        idTeam = data.getString("idTeam")
                    )
                )
            }
            uiThread {
                nextMatchView.showDataTeams(dataItems)
                nextMatchView.hideLoading()
            }
        }
    }

}