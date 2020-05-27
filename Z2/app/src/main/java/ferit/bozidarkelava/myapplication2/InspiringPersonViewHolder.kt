package ferit.bozidarkelava.myapplication2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ferit.bozidarkelava.myapplication2.interfaces.quoteOnClick
import kotlinx.android.synthetic.main.item.view.*

//abstract
class InspiringPersonViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun populateOnViewHolder (person: InspiringPerson, personOnClick: quoteOnClick) {
        itemView.tvName.text = "" + person.name + " " + person.lastName
        itemView.tvDates.text = person.birthday
        itemView.tvInformation.text = person.description
        itemView.civImage.setOnClickListener {
            personOnClick.getQuote(person.id)
        }

        itemView.btnDeleteItem.setOnClickListener() {
            personOnClick.delete(person.id)
        }

        itemView.btnEdit.setOnClickListener( {
            personOnClick.edit(person.id)
        })
    }
}