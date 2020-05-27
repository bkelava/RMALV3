package ferit.bozidarkelava.myapplication2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ferit.bozidarkelava.myapplication2.interfaces.quoteOnClick
import kotlinx.android.synthetic.main.item.view.*

class InspiringPersonAdapter(famousPersons: MutableList<InspiringPerson>, personClick : quoteOnClick, context: Context)
    : RecyclerView.Adapter<InspiringPersonViewHolder>() {

    private val famousPersons : MutableList<InspiringPerson> = mutableListOf()
    private val personOnClick : quoteOnClick
    private val context : Context

    init {
        this.famousPersons.addAll(famousPersons)
        this.personOnClick =  personClick
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspiringPersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val holder = InspiringPersonViewHolder(view)
        return holder

    }

    override fun getItemCount(): Int {
        val size : Int = famousPersons.size
        return size
    }

    override fun onBindViewHolder(holder: InspiringPersonViewHolder, position: Int) {
        val person = famousPersons[position]
        holder.populateOnViewHolder(person, personOnClick)

        Picasso.with(context).load(person.image).into(holder.itemView.civImage)
    }
}