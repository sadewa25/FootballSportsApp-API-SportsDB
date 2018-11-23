package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.submenu.overview

import android.content.Context
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class OverviewPresenter(val apiClient: APIClient,
                        val context: Context?) {

    fun getDescTeams(id:String,textDesc: TextView){
        var datas = ""
        doAsync {
            val jsonObject = JSONObject(apiClient.getResponse(context?.getString(R.string.url_detail_teams)+id))
            val arrayEvents = jsonObject.getJSONArray("teams")
            for (i in 0..arrayEvents.length()-1) {
                val data = arrayEvents.getJSONObject(i)
                datas = data.getString("strDescriptionEN")
            }
            uiThread {
                textDesc.text = datas
            }
        }
    }

}