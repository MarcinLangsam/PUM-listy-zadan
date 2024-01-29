package com.example.lista1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    fun endQuiz()
    {
        QuestionNumber.text = "Gratulacje"
        ProgressBar.visibility = View.GONE
        A.visibility = View.GONE
        B.visibility = View.GONE
        C.visibility = View.GONE
        D.visibility = View.GONE
        QuestionText.text = "Zdobyłeś " + points + " punktów"
        NextButton.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        QuestionNumber.text = "Pytanie: " + QuestionCout
        QuestionText.text = QuestionTable[QuestionCout-1][0].toString()
        A.text = QuestionTable[QuestionCout-1][1].toString()
        B.text = QuestionTable[QuestionCout-1][2].toString()
        C.text = QuestionTable[QuestionCout-1][3].toString()
        D.text = QuestionTable[QuestionCout-1][4].toString()

        NextButton.setOnClickListener{
            if (answer == QuestionTable[QuestionCout - 1][5]) {
                Log.i(TAG,"+10");
                points += 10
            }
            if(QuestionCout <= 9) {

                Log.i(TAG,answer);
                Log.i(TAG,QuestionTable[QuestionCout - 1][5]);
                QuestionCout++
                QuestionNumber.text = "Pytanie: " + QuestionCout
                QuestionText.text = QuestionTable[QuestionCout - 1][0].toString()
                A.text = QuestionTable[QuestionCout - 1][1].toString()
                B.text = QuestionTable[QuestionCout - 1][2].toString()
                C.text = QuestionTable[QuestionCout - 1][3].toString()
                D.text = QuestionTable[QuestionCout - 1][4].toString()


                ProgressBar.progress++
                RG.clearCheck()

            }
            else{
                endQuiz()
            }

        }

        A.setOnClickListener{
            answer = A.text.toString()
        }
        B.setOnClickListener{
            answer = B.text.toString()
        }
        C.setOnClickListener{
            answer = C.text.toString()
        }
        D.setOnClickListener{
            answer = D.text.toString()
        }
    }
    private val TAG = "MyActivity"
    private val QuestionNumber: TextView by lazy { findViewById(R.id.QuestionNumber) }
    private var QuestionCout = 1
    private var points = 0
    private var answer = ""
    var QuestionTable = arrayOf (
        arrayOf("Na której z flag świata widnieje ludzka postać?","Brazylii","Laosu","Belize","Trynidadu i Tobago",   "Belize"),
        arrayOf("W którym roku urodził się Fryderyk Chopin?","1815","1810","1806","1800",  "1810"),
        arrayOf("Co oznacza w języku francuskim wyraz 'foret'?","formuła","foremka","korek","las",  "las"),
        arrayOf("Frutarianie jedzą?","nabiał","surowe jajka","surowe owoce","ryby",  "surowe owoce"),
        arrayOf("Jeżeli cegła waży 2kg i pół cegły, to ile waży cała cegła?","2kg","2,5kg","4kg","6,5kg",  "4kg"),
        arrayOf("W którym roku powstał serwis internetowy YouTube?","1995","2001","2005","2007",  "2005"),
        arrayOf("Ile pięter jest w Pałacu Kultury i Nauki w Warszawie?","30","42","48","50",  "42"),
        arrayOf("Jak nazywa się kirgiska jednostka monetarna?","Jen","Rubel","Nid","Som",  "Som"),
        arrayOf("Gdzie jest umiejscowione serce krewetki?","w głowie","w ogonku","w wątrobie","kewetka nie ma serce",  "w głowie"),
        arrayOf("Kto był honorowym burmistrzem miasta Talkeetna na Alasce przez 20 lat?","Boris Johnson","kot Stubbs","Donald Trump","Madonna",  "kot Stubbs")
    )

    private val NextButton: Button by lazy { findViewById(R.id.NextButton)}
    private val ProgressBar: ProgressBar by lazy { findViewById(R.id.QuestionProgressBar) }
    private val QuestionText: TextView by lazy { findViewById(R.id.QuestionText) }
    private val A: RadioButton by lazy { findViewById(R.id.A) }
    private val B: RadioButton by lazy { findViewById(R.id.B) }
    private val C: RadioButton by lazy { findViewById(R.id.C) }
    private val D: RadioButton by lazy { findViewById(R.id.D) }
    private val RG: RadioGroup by lazy { findViewById(R.id.RadioGroup) }
}