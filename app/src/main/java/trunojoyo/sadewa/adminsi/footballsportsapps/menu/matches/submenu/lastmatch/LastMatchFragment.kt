package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.lastmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.detail.DetailActivity
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchAdapter
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchModel
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchSpinner
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient


class LastMatchFragment : Fragment(),LastMatchView {

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }
    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun showDataLeague(dataItems: ArrayList<MatchModel>) {
        dataItemsLeague.addAll(dataItems)
        adapter.notifyDataSetChanged()
    }

    override fun showDataEvents(dataItems: ArrayList<MatchModel>) {
        dataItemsEvents.clear()
        dataItemsEvents.addAll(dataItems)
        adapterRecycler.notifyDataSetChanged()
    }

    private lateinit var lastMatchPresenter: LastMatchPresenter
    private lateinit var apiClient: APIClient
    private lateinit var dataItemsLeague:ArrayList<MatchModel>
    private lateinit var dataItemsEvents:ArrayList<MatchModel>
    private lateinit var adapter:MatchSpinner
    private lateinit var adapterRecycler: MatchAdapter
    private lateinit var loading: ProgressBar
    var idLeague:String = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_last_match, container, false)

        val spin:Spinner = view.find(R.id.spinner_last_match)
        val recycler:RecyclerView = view.find(R.id.recycler_last_match)
        loading = view.find(R.id.loading)
        apiClient = APIClient()
        lastMatchPresenter = LastMatchPresenter(this,apiClient,context)
        dataItemsLeague = arrayListOf()
        dataItemsEvents = arrayListOf()

        adapter = MatchSpinner(context,dataItemsLeague)

        spin.adapter = adapter

        lastMatchPresenter.getDataLeague("${context?.getString(R.string.url_league)}")

        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                idLeague = dataItemsLeague[position].idLeague
                lastMatchPresenter.getEventsLeagues(idLeague)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {}
        }

        //recycler
        dataItemsEvents = arrayListOf()
        val layout:RecyclerView.LayoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layout
        adapterRecycler = MatchAdapter(dataItemsEvents, context = context,param = 2) {
            startActivity<DetailActivity>("id" to it.idEvent, "param" to "1")
        }
        recycler.adapter = adapterRecycler
        lastMatchPresenter.getEventsLeagues(context?.getString(R.string.url_events_next_league)+idLeague)

        val search: EditText = view.find(R.id.search_last_match)
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(edit: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, position: Int, p2: Int, p3: Int) {
                adapterRecycler.filter.filter(s.toString())
            }
        })

        return view
    }


}
