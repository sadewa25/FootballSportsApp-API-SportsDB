package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.lastmatch

import android.content.Context
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchModel
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class LastMatchPresenter(val lastMatchView: LastMatchView,
                         val apiClient: APIClient,
                         val context: Context?): AnkoLogger {
    fun getDataLeague(url: String){
        val dataItems:ArrayList<MatchModel> = arrayListOf()
        doAsync {
            val jsonObject = JSONObject(apiClient.getResponse(url))
            val arrayEvents = jsonObject.getJSONArray("countrys")
            for (i in 0..arrayEvents.length()-1) {
                val data = arrayEvents.getJSONObject(i)
                dataItems.add(
                    MatchModel(
                    data.getString("idLeague"),
                    data.getString("strLeague")
                )
                )
            }
            uiThread {
                lastMatchView.showDataLeague(dataItems)
            }
        }
    }

    fun getDataEvents(idEvents:ArrayList<String>){
        val dataItems:ArrayList<MatchModel> = arrayListOf()
        doAsync {
            for (i in 0 until idEvents.size-1){
                val jsonObject = JSONObject(apiClient.getResponse(context?.getString(R.string.url_events_detail)+idEvents[i]))
                val arrayEvents = jsonObject.getJSONArray("events")
                for (i in 0..arrayEvents.length()-1) {
                    val data = arrayEvents.getJSONObject(i)
                    dataItems.add(
                        MatchModel(
                            dateEvent = data.getString("dateEvent"),
                            strTime = data.getString("strTime"),
                            strHomeTeam = data.getString("strHomeTeam"),
                            strAwayTeam = data.getString("strAwayTeam"),
                            intAwayScore = data.getString("intAwayScore"),
                            intHomeScore = data.getString("intHomeScore"),
                            idEvent = data.getString("idEvent")
                        )
                    )
                }
            }
            uiThread {
                lastMatchView.showDataEvents(dataItems)
                lastMatchView.hideLoading()
            }
        }
    }

    fun getEventsLeagues(id:String){
        lastMatchView.showLoading()
        val dataID:ArrayList<String> = arrayListOf()
        doAsync {
            val jsonObject = JSONObject(apiClient.getResponse(context?.getString(R.string.url_events_last_league)+id))
            val arrayEvents = jsonObject.getJSONArray("events")
            for (i in 0..arrayEvents.length()-1) {
                val data = arrayEvents.getJSONObject(i)
                dataID.add(data.getString("idEvent"))
            }
            uiThread {
                getDataEvents(dataID)
            }
        }
    }
}