package ferit.bozidarkelava.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import ferit.bozidarkelava.myapplication2.fragments.FragmentEditPerson
import ferit.bozidarkelava.myapplication2.fragments.FragmentStart
import ferit.bozidarkelava.myapplication2.interfaces.Communicator

class MainActivity : AppCompatActivity(),
    Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentStart =
            FragmentStart()

        //pri pokretanju aplikacije odmah se postavlja prvi fragment na ekran
        supportFragmentManager.beginTransaction().replace(R.id.mainRelativLayout,
            FragmentStart()
        ).commit()


    }

    override fun passData(input: String) {
        val bundle = Bundle()
        bundle.putString("KEY", input)
        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentEditPerson =
            FragmentEditPerson()
        fragmentEditPerson.arguments = bundle

        transaction.replace(R.id.startFrameLayout, fragmentEditPerson)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}
