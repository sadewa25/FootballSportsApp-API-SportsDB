package trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.submenu.players

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import trunojoyo.sadewa.adminsi.footballsportsapps.R
import trunojoyo.sadewa.adminsi.footballsportsapps.menu.teams.detailteams.DetailTeamsModel

class PlayersAdapter(var dataItems:ArrayList<DetailTeamsModel>,
                     private val listener: (DetailTeamsModel) -> Unit):
    RecyclerView.Adapter<PlayersAdapter.itemHolder>(), AnkoLogger {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): itemHolder {
        return itemHolder(itemAdapterUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = dataItems.size

    override fun onBindViewHolder(holder: itemHolder, position: Int) {
        holder.bindItems(dataItems[position], Picasso.get(),listener)
    }

    class itemHolder(itemView: View) : RecyclerView.ViewHolder(itemView), AnkoLogger {
        var itemPhoto: ImageView = itemView.find(R.id.players_photo)
        var itemName: TextView = itemView.find(R.id.players_name)
                var itemPosition:TextView = itemView.find(R.id.players_position)
        fun bindItems(model: DetailTeamsModel, picasso: Picasso, listener: (DetailTeamsModel) -> Unit){
            picasso.load(model.strCutout).placeholder(R.mipmap.ic_launcher).into(itemPhoto)
            itemName.text = model.strPlayer
            itemPosition.text = model.strPosition
            itemView.setOnClickListener { listener(model) }
        }
    }


    //ItemViewUI
    class itemAdapterUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(8)

                    imageView {
                        id = R.id.players_photo
                        padding = dip(8)
                    }.lparams(width= 200,height = 200)
                    textView {
                        id = R.id.players_name
                        padding = dip(8)
                        gravity = Gravity.CENTER
                    }.lparams(width= 0,height = matchParent, weight = 0.5f)
                    textView{
                        id = R.id.players_position
                        padding = dip(8)
                        gravity = Gravity.CENTER
                    }.lparams(width= 0,height = matchParent,weight = 0.5f)
                }
            }
        }

    }


}