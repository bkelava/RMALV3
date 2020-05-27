package ferit.bozidarkelava.myapplication2.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ferit.bozidarkelava.myapplication2.Database
import ferit.bozidarkelava.myapplication2.InspiringPerson
import ferit.bozidarkelava.myapplication2.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_edit_person.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentEditPerson : Fragment() {

    val database = Database.getInstance().inspiringPersonDao()
    var name: String = ""
    var lastName: String = ""
    var birthday: String = ""
    var description: String = ""
    var image: String = ""
    var quotes: String = ""
    var index: String = ""

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_edit_person, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack: Button = view.findViewById(R.id.btnBackToStart)
        btnBack.setOnClickListener {
            val mContext = activity as AppCompatActivity
            mContext.supportFragmentManager.beginTransaction().replace(R.id.frameEditPersonLayout, FragmentStart()).commit()
        }

        index = arguments?.getString("KEY").toString()
        var mIndex: Int? = index.toIntOrNull()
        val btnSave: Button = view.findViewById(R.id.btnSave)
        btnSave.setOnClickListener() {
            if (etEditName.text.toString().trim().isEmpty() ||
                etEditLastName.text.toString().trim().isEmpty() ||
                etEditDate.text.toString().trim().isEmpty() ||
                etEditInfo.text.toString().trim().isEmpty() ||
                etEditQuotes.text.toString().trim().isEmpty()
            ) {
                Toast.makeText(activity, "YOU ARE MISSING SOMETHING", Toast.LENGTH_LONG).show()
            } else {
                val inspiringPerson: InspiringPerson
                name = etEditName.text.toString()
                lastName = etEditLastName.text.toString()
                birthday = etEditDate.text.toString()
                description = etEditInfo.text.toString()
                var position: Int = mIndex!!
                quotes = etEditQuotes.text.toString()
                image = etEditImage.text.toString().trim()

                if (image.isEmpty() || !URLUtil.isValidUrl(image))
                {
                    inspiringPerson = InspiringPerson(position, name, lastName, birthday, description,"https://images-na.ssl-images-amazon.com/images/I/71OREnda8GL._AC_SL1379_.jpg", quotes)
                }
                else {
                    inspiringPerson = InspiringPerson(position, name, lastName, birthday, description, image, quotes)
                }
                database.update(inspiringPerson = inspiringPerson)
            }
            reset()
        }
    }

    private fun reset() {
        etEditName.setText("")
        etEditLastName.setText("")
        etEditDate.setText("")
        etEditInfo.setText("")
        etEditImage.setText("")
        etEditQuotes.setText("")
    }
}