package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.submenu.players


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.DetailTeamsModel
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.detailplayer.DetailPlayerActivity
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient


class PlayersFragment() : Fragment(),PlayersView,AnkoLogger {

    override fun getListPlayers(dataItems: ArrayList<DetailTeamsModel>) {
        this.dataItems.addAll(dataItems)
        playersAdapter.notifyDataSetChanged()
    }

    private lateinit var id:String
    @SuppressLint("ValidFragment")
    constructor(id:String) : this() {
        this.id = id
    }
    private lateinit var playersPresenter: PlayersPresenter
    private lateinit var playersAdapter: PlayersAdapter
    private lateinit var dataItems: ArrayList<DetailTeamsModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_players, container, false)

        dataItems = arrayListOf()
        playersPresenter = PlayersPresenter(this, APIClient(),context)
        playersAdapter = PlayersAdapter(dataItems){
            startActivity<DetailPlayerActivity>("id" to it.idPlayer)
        }

        val recycler:RecyclerView = view.find(R.id.players_recycler)
        val layout:RecyclerView.LayoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layout
        recycler.adapter = playersAdapter

        playersPresenter.getListTeams(id)

        return view
    }

}
