package trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.db.FavouriteTeamsModel
import trunojoyo.sadewa.adminsi.footballsportsapps.db.database
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.DetailTeamsActivity


class FavouriteTeamsFragment : Fragment(),
    FavouriteTeamsView,SwipeRefreshLayout.OnRefreshListener {

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showDataItemsFavourite(dataItems: List<FavouriteTeamsModel>) {
        this.dataItems.clear()
        this.dataItems.addAll(dataItems)
        favouriteTeamsAdapter.notifyDataSetChanged()
    }

    override fun onRefresh() {
        refresh.isRefreshing = false
        favouriteTeamsPresenter.getDataItemsFavourite()
        favouriteTeamsAdapter.notifyDataSetChanged()
    }

    private lateinit var favouriteTeamsAdapter: FavouriteTeamsAdapter
    private lateinit var favouriteTeamsPresenter: FavouriteTeamsPresenter
    private var dataItems:ArrayList<FavouriteTeamsModel> = arrayListOf()
    private lateinit var refresh:SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_favourite_teams, container, false)

        val recycler:RecyclerView = view.find(R.id.favourite_recycler_main)
         refresh = view.find(R.id.refresh_layout)

        favouriteTeamsPresenter = FavouriteTeamsPresenter(this,context!!.database)

        favouriteTeamsAdapter = FavouriteTeamsAdapter(dataItems){
            startActivity<DetailTeamsActivity>("id" to it.idTeam)
        }

        val layout:RecyclerView.LayoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layout
        recycler.adapter = favouriteTeamsAdapter

        favouriteTeamsPresenter.getDataItemsFavourite()

        refresh.setOnRefreshListener(this)

        return view
    }


}
