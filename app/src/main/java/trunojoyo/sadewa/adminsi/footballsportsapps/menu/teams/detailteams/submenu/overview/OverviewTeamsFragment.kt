package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.submenu.overview

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.find

import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient


class OverviewTeamsFragment() : Fragment() {

    private lateinit var id:String
    @SuppressLint("ValidFragment")
    constructor(id: String) : this() {
        this.id = id
    }

    private lateinit var overviewPresenter: OverviewPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_overview_teams, container, false)

        val titleDesc:TextView = view.find(R.id.overview_desc)

        overviewPresenter = OverviewPresenter(APIClient(),context)
        overviewPresenter.getDescTeams(id,titleDesc)

        return view
    }

}
