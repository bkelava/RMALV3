package ferit.bozidarkelava.myapplication2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ferit.bozidarkelava.myapplication2.Database
import ferit.bozidarkelava.myapplication2.InspiringPerson
import ferit.bozidarkelava.myapplication2.R
import kotlinx.android.synthetic.main.fragment_add.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentAdd : Fragment() {

    val database = Database.getInstance().inspiringPersonDao()
    internal var id = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View  = inflater.inflate(R.layout.fragment_add, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBacktoStart: Button = view.findViewById(R.id.btnBack)
        btnBacktoStart.setOnClickListener {
            val context = activity as AppCompatActivity
            context.supportFragmentManager.beginTransaction().replace(
                R.id.addFrameLayout,
                FragmentStart()
            ).commit()
        }
        var btnAdd: ImageView =view.findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            if (etName.text.toString().trim().isEmpty() ||
                etLastName.text.toString().trim().isEmpty() ||
                etDate.text.toString().trim().isEmpty() ||
                etInfo.text.toString().trim().isEmpty() ||
                etQuotes.text.toString().trim().isEmpty()
            ) {
                Toast.makeText(activity, "IMAGE IS OPTIONAL, THE REST IS MANDATORY", Toast.LENGTH_LONG).show()
            } else {
                val name: String = etName.text.toString()
                val lastName: String = etLastName.text.toString()
                val birtdate: String = etDate.text.toString()
                val information: String = etInfo.text.toString()
                var imageURL: String = etImage.text.toString()
                if (URLUtil.isValidUrl(imageURL) == false) {
                    imageURL = "https://images-na.ssl-images-amazon.com/images/I/71OREnda8GL._AC_SL1379_.jpg" //default vrijednost
                }
                val quotes = etQuotes.text.toString()

                val id: Int = database.selectMaxElement() + 1
                val inspiringPerson = InspiringPerson(id, name, lastName, birtdate, information, imageURL, quotes)
                database.insert(inspiringPerson)
                reset()
            }
        }
    }

    private fun reset() {
        etName.setText("")
        etLastName.setText("")
        etDate.setText("")
        etInfo.setText("")
        etImage.setText("")
        etQuotes.setText("")
    }
}
