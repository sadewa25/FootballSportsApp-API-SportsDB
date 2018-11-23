package trunojoyo.sadewa.adminsi.footballsportsapps.menu.favourite.submenu.matches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.find

import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.db.Favourite
import trunojoyo.sadewa.adminsi.footballsportsapps.db.database


class FavouriteMatchesFragment : Fragment(),
    FavouriteView,SwipeRefreshLayout.OnRefreshListener {

    override fun onRefresh() {
        dataItems.clear()
        presenter.getDataItemsFavourite()
        adapter.notifyDataSetChanged()
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showDataItemsFavourite(dataItems: List<Favourite>) {
        this.dataItems.addAll(dataItems)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterFavouriteMatch
    private lateinit var presenter: FavouritePresenter
    private lateinit var dataItems: ArrayList<Favourite>
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_favourite_matches, container, false)

        swipeRefreshLayout = view.find(R.id.refresh_layout)
        swipeRefreshLayout.setOnRefreshListener(this)

        recyclerView = view.find(R.id.favourite_recycler_main)
        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        presenter = FavouritePresenter(
            this,
            context!!.database
        )
        dataItems = ArrayList()
        presenter.getDataItemsFavourite()

        adapter = AdapterFavouriteMatch(
            dataItems,
            context
        )
        recyclerView.adapter = adapter

        return  view
    }


}
