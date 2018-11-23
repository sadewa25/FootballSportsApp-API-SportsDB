package trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.nextmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.detail.DetailActivity
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchAdapter
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchSpinner
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.matches.submenu.MatchModel
import trunojoyo.sadewa.adminsi.footballsportsapps.network.APIClient

class NextMatchFragment : Fragment(), NextMatchView,AnkoLogger {

    override fun showDataEvents(dataItems: ArrayList<MatchModel>) {
        dataItemsEvents.clear()
        dataItemsEvents.addAll(dataItems)
        adapterRecycler.notifyDataSetChanged()
    }

    override fun showDataLeague(dataItems: ArrayList<MatchModel>) {
        dataItemsLeague.addAll(dataItems)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    private lateinit var nextMatchPresenter: NextMatchPresenter
    private lateinit var apiClient: APIClient
    private lateinit var dataItemsLeague:ArrayList<MatchModel>
    private lateinit var dataItemsEvents:ArrayList<MatchModel>
    private lateinit var adapter: MatchSpinner
    private lateinit var adapterRecycler: MatchAdapter
    private lateinit var loading:ProgressBar
    var idLeague:String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_next_match, container, false)

        loading = view.find(R.id.loading)
        setHasOptionsMenu(true)

        val spinner:Spinner = view.find(R.id.spinner_next_match)
        apiClient = APIClient()
        dataItemsLeague = arrayListOf()
        nextMatchPresenter = NextMatchPresenter(this, apiClient,context)

        adapter = MatchSpinner(
            context,
            dataItemsLeague
        )
        spinner.adapter = adapter

        nextMatchPresenter.getDataLeague("${context?.getString(R.string.url_league)}")

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                idLeague = dataItemsLeague[position].idLeague
                nextMatchPresenter.getEventsLeagues(context?.getString(R.string.url_events_next_league)+idLeague)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
            }
        }

        //recycler
        val recyclerView:RecyclerView = view.find(R.id.recycler_next_match)
        dataItemsEvents = arrayListOf()
        val layout:RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layout
        adapterRecycler = MatchAdapter(dataItemsEvents, context = context,param = 1) {
            startActivity<DetailActivity>("id" to it.idEvent, "param" to "2")
        }
        recyclerView.adapter = adapterRecycler
        nextMatchPresenter.getEventsLeagues(context?.getString(R.string.url_events_next_league)+idLeague)

        val search:EditText = view.find(R.id.search_next_match)
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(edit: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, position: Int, p2: Int, p3: Int) {
                adapterRecycler.filter.filter(s.toString())
            }
        })

        return view
    }


}
