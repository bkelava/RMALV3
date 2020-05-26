package hr.ferit.bozidarkelava.birdsofprey

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val colorGREEN: String = "GREEN"
    private val colorBLUE: String = "BLUE"
    private val colorRED: String = "RED"
    private  val colorYELLOW: String = "YELLOW"
    private val colorBLACK: String = "BLACK"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
    }

    override fun onResume() {
        super.onResume()
        display()
    }

    private fun display() {
        val mCounter = PreferenceManager().getCounter()
        val color = PreferenceManager().getColor()

        tvCount.text = mCounter
        color?.let { setColor(it) } //ovo je alt+ENTER "pametni" fix
    }

    private fun setUpUI() {
        btnFirstColor.setOnClickListener() {
            saveState(colorGREEN)
        }
        btnSecondColor.setOnClickListener() {
            saveState(colorBLUE)
        }
        btnThirdColor.setOnClickListener(){
            saveState(colorRED)
        }
        btnFourthColor.setOnClickListener() {
            saveState(colorYELLOW)
        }
        btnRestart.setOnClickListener(){
            restartState()
        }
    }

    private fun restartState() {
        val preferenceManager = PreferenceManager()
        preferenceManager.saveCounter("0")
        tvCount.text = "0"
        setColor(colorBLACK)
        preferenceManager.saveColor(colorBLACK)
    }

    private fun saveState(color: String) {
        val preferenceManager = PreferenceManager()

        var counter: String = tvCount.text.toString()
        var mCounter: Int = counter.toInt()

        mCounter += 1 //mCounter = mCounter + 1

        preferenceManager.saveColor(color)
        preferenceManager.saveCounter(mCounter.toString())

        display()
    }

    private fun setColor(color: String) {
        if (color == colorGREEN) {
            tvCount.setBackgroundColor(Color.GREEN)
        }
        else if (color == colorRED) {
            tvCount.setBackgroundColor(Color.RED)
        }
        else if (color == colorBLUE) {
            tvCount.setBackgroundColor(Color.BLUE)
        }
        else if (color == colorYELLOW) {
            tvCount.setBackgroundColor(Color.YELLOW)
        }
        else if (color == colorBLACK) {
            tvCount.setBackgroundColor(Color.BLACK)
        }
    }
}


/*
btnFirstColor.setOnClickListener() {
    val preferenceManager = PreferenceManager()

    var mCounter = tvCount.text.toString()
    var counter: Int = mCounter.toInt()
    counter += 1
    tvCount.text = counter.toString()
    preferenceManager.saveCounter(counter.toString())
    setColor("GREEN")
    preferenceManager.saveColor("GREEN")
}
btnSecondColor.setOnClickListener() {
    val preferenceManager = PreferenceManager()

    var mCounter = tvCount.text.toString()
    var counter: Int = mCounter.toInt()
    counter += 1
    tvCount.text = counter.toString()
    preferenceManager.saveCounter(counter.toString())
    setColor("BLUE")
    preferenceManager.saveColor("BLUE")
} */