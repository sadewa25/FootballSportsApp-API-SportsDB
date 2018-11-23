package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.submenu.players

import android.content.Context
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.DetailTeamsModel
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class PlayersPresenter(val playersView: PlayersView,
                       val apiClient: APIClient,
                       val context: Context?):AnkoLogger{

    fun getListTeams(id:String){
        val dataItems:ArrayList<DetailTeamsModel> = arrayListOf()
        doAsync {
            val jsonObject = JSONObject(apiClient.getResponse(context?.getString(R.string.url_detail_list_players)+id))
            val arrayEvents = jsonObject.getJSONArray("player")
            for (i in 0..arrayEvents.length()-1) {
                val data = arrayEvents.getJSONObject(i)
                dataItems.add(
                    DetailTeamsModel(
                        strCutout = data.getString("strCutout"),
                        strPlayer = data.getString("strPlayer"),
                        strPosition = data.getString("strPosition"),
                        idPlayer = data.getString("idPlayer")
                    )
                )
            }
            uiThread {
                playersView.getListPlayers(dataItems)
            }

        }
    }

}

