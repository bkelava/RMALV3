package ferit.bozidarkelava.myapplication2.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ferit.bozidarkelava.myapplication2.Database
import ferit.bozidarkelava.myapplication2.InspiringPerson
import ferit.bozidarkelava.myapplication2.InspiringPersonAdapter
import ferit.bozidarkelava.myapplication2.R
import ferit.bozidarkelava.myapplication2.interfaces.Communicator
import ferit.bozidarkelava.myapplication2.interfaces.quoteOnClick
import kotlinx.android.synthetic.main.fragment_start.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentStart : Fragment() {

    val database = Database.getInstance().inspiringPersonDao()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View  = inflater.inflate(R.layout.fragment_start, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var communicator: Communicator
        communicator = activity as Communicator

        val btnAdd: Button = view.findViewById(R.id.btnAddNewPerson)
        btnAdd.setOnClickListener {
            val mContext = activity as AppCompatActivity
            mContext.supportFragmentManager.beginTransaction().replace(R.id.startFrameLayout, FragmentAdd()).commit()
        }

        val personClick = object : quoteOnClick {
            override fun getQuote(index: Int) {
                var inspiringPersonDao = database.selectId(index)
                var quotes: List<String> = inspiringPersonDao.quotes.split(";")
                var size = quotes.size - 1
                val random = (0..size).random()
                val mContext = activity as AppCompatActivity
                Toast.makeText(mContext, quotes[random], Toast.LENGTH_LONG).show()
            }

            override fun delete(position: Int) {
                var inspiringPerson = database.selectId(position)
                database.delete(inspiringPerson)

                val mContext = activity as AppCompatActivity  //ova i sljedeca linija koda sluze za refresh fragmenta nnakon brisanja itema
                mContext.supportFragmentManager.beginTransaction().detach(this@FragmentStart).attach(this@FragmentStart).commit()
            }

            override fun edit(index: Int) {
                communicator.passData(index.toString())
            }
        }
        val context = activity as AppCompatActivity
        rvFamousPersons.layoutManager =  LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvFamousPersons.adapter = InspiringPersonAdapter(database.selectAll() as MutableList<InspiringPerson>, personClick, context)

        btnExit.setOnClickListener {
            ActivityCompat.finishAffinity(Activity())
            System.exit(-1)
        }
    }
}
