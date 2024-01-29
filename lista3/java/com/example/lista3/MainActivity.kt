package com.example.lista3

import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lista3.ui.theme.Lista3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterExample()
                }
            }
        }
    }

    var points = 0
    var answer = ""
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

    @Composable
    fun KindRadioGroup(
        mItems: List<String>,
        selected: String,
        setSelected: (selected: String) -> Unit,
    ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                mItems.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selected == item,
                            onClick = {
                                setSelected(item)

                            },
                            enabled = true,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Blue
                            )
                        )
                        Text(text = item, modifier = Modifier.padding(start = 8.dp), fontSize = 30.sp )
                    }
                }
            }
    }

    @Composable
    fun CounterExample() {
        val QuestionCout: MutableState<Int> = rememberSaveable {
            mutableStateOf(1)
        }
        val kinds = listOf(QuestionTable[QuestionCout.value - 1][1].toString(), QuestionTable[QuestionCout.value - 1][2].toString(), QuestionTable[QuestionCout.value - 1][3].toString(), QuestionTable[QuestionCout.value - 1][4].toString())
        val (selected, setSelected) = remember { mutableStateOf("") }
        val visible =  remember { mutableStateOf(true)}
        val Text = remember {mutableStateOf("Pytanie "+ QuestionCout.value +" /10")}
        val TextPoints = remember {mutableStateOf(QuestionTable[QuestionCout.value - 1][0].toString())}

        Column(
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
            //modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = Text.value,
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 10.dp).padding(vertical = 10.dp)
            )

            LinearProgressIndicator(
                progress = (QuestionCout.value.toFloat()/10.0f),
                modifier = Modifier.fillMaxWidth().height(10.dp).alpha(if (visible.value) 1f else 0f),
                color = Color.Blue,

            )

            Card (modifier = Modifier.padding(10.dp)) {

                Text(
                    text = TextPoints.value,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
            
            Card(modifier = Modifier.alpha(if (visible.value) 1f else 0f),) {
                KindRadioGroup(
                    mItems = kinds,
                    selected, setSelected

                )
                
            }

                Button(
                    onClick = {
                        answer = selected
                        if (answer == QuestionTable[QuestionCout.value - 1][5].toString()) {
                            points += 10
                        }

                        if (QuestionCout.value > 9) {
                            Text.value = "Gratulacje!\nZdobyłes"
                            TextPoints.value = points.toString()+" / 100 punktów"
                            visible.value = false
                        } else {
                            QuestionCout.value++
                            Text.value = "Pytanie "+ QuestionCout.value +" /10"
                            TextPoints.value = QuestionTable[QuestionCout.value - 1][0].toString()
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp).alpha(if (visible.value) 1f else 0f),
                    shape = RectangleShape
                ) {
                    Text(text = "Następne pytanie", fontSize = 40.sp)
                }
            }

    }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        CounterExample()
    }

