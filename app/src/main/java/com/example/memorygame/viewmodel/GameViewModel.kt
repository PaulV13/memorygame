package com.example.memorygame.viewmodel


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorygame.R
import com.example.memorygame.model.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameViewModel @Inject constructor(): ViewModel() {

    private val allCards: MutableList<Card> = mutableListOf(
        Card(1, "bulbasaur", R.drawable.bulbasaur),
        Card(2,"caterpie", R.drawable.caterpie),
        Card(3,"clefairy", R.drawable.clefairy),
        Card(4,"pidgey", R.drawable.pidgey),
        Card(5,"pikachu", R.drawable.pikachu),
        Card(6,"charmander", R.drawable.charmander),
        Card(7,"diglett", R.drawable.diglett),
        Card(8,"ekans", R.drawable.ekans),
        Card(9,"jigglypuff", R.drawable.jigglypuff),
        Card(10,"nidoran", R.drawable.nidoran),
        Card(11,"oddish", R.drawable.oddish),
        Card(12,"rattata", R.drawable.rattata),
        Card(13,"squirtle", R.drawable.squirtle),
        Card(14,"sandshrew", R.drawable.sandshrew),
        Card(15,"zubat", R.drawable.zubat)
    )

    private var _cards: MutableState<MutableList<Card>> = mutableStateOf(mutableListOf())
    var cards: MutableState<MutableList<Card>> = _cards

    private var _movement: MutableState<Int> = mutableStateOf(0)
    var movement: MutableState<Int> = _movement

    private var _parCount: MutableState<Int> = mutableStateOf(0)
    var parCount: MutableState<Int> = _parCount

    private var _columns: MutableState<Int> = mutableStateOf(0)
    var columns: MutableState<Int> = _columns

    private var _clicks: MutableState<Int> = mutableStateOf(0)
    var clicks: MutableState<Int> = _clicks

    private var _time: MutableState<Int> = mutableStateOf(60)
    var time: MutableState<Int> = _time

    private var _openDialog: MutableState<Boolean> = mutableStateOf(false)
    var openDialog: MutableState<Boolean> =  _openDialog

    private var _match: MutableState<Boolean> = mutableStateOf(false)
    var match: MutableState<Boolean> = _match

    private var _list: MutableState<MutableList<Int>> = mutableStateOf(mutableListOf())
    val list: MutableState<MutableList<Int>> = _list

    private var _choiceIndex1: MutableState<Int> = mutableStateOf(-1)
    val choiceIndex1: MutableState<Int> = _choiceIndex1

    private var _choiceIndex2: MutableState<Int> = mutableStateOf(-1)
    val choiceIndex2: MutableState<Int> = _choiceIndex2

    private var _timerStarting: MutableState<Boolean> = mutableStateOf(false)
    var timerStarting: MutableState<Boolean> = _timerStarting

    fun getIndex1(index: Int){
        timerStarting.value = true
        _cards.value[index].isVisible = true
        _choiceIndex1.value = index
    }

    fun getIndex2(index: Int){
        _cards.value[index].isVisible = true
        _choiceIndex2.value = index
        viewModelScope.launch {
            delay(500)
            compareCard()
        }
        _movement.value++
    }

    fun updateColumns(number: Int){
        _columns.value = number
    }

    fun updateCards(){
        getRandomList()
    }

    fun updateCountPar(countPar: Int){
        _parCount.value = countPar
    }

    private fun compareCard(){
        match.value = _cards.value[choiceIndex1.value].name == _cards.value[choiceIndex2.value].name
        if(match.value){
            _choiceIndex1.value = -1
            _choiceIndex2.value = -1
            parCountDown()
        }else{
            _cards.value[choiceIndex1.value].isVisible = false
            _cards.value[choiceIndex2.value].isVisible = false
            _choiceIndex1.value = -1
            _choiceIndex2.value = -1
        }
    }

    private fun parCountDown(){
        _parCount.value--
        if(_parCount.value == 0){
            showAlert()
        }
    }

    fun showAlert(){
        _openDialog.value = true
    }

    fun resetGame(){
        _parCount.value = cards.value.size / 2
        _choiceIndex1.value = -1
        _choiceIndex2.value = -1
        _time.value = 60
        _movement.value = 0
        _clicks.value = 0
        _match.value = false
        _list.value = mutableListOf()
        _timerStarting.value = false
    }

    fun getRandomList(){
        _cards.value = mutableListOf()
        var count = parCount.value
        for(x in 0 until _parCount.value){
            var randomIndex = Random.nextInt(allCards.size)
            var randomElement = allCards[randomIndex]
            randomElement.isVisible = false

            while(_cards.value.contains(randomElement)){
                randomIndex = Random.nextInt(allCards.size)
                randomElement = allCards[randomIndex]
                randomElement.isVisible = false
            }

            _cards.value.add(randomElement)

            count++
            val newCard = Card(
                count,
                randomElement.name,
                randomElement.image,
                randomElement.imageBack,
                false
            )

            _cards.value.add(newCard)

        }
        _cards.value.shuffle()
    }

    fun startTimer() {
        time.value--
    }
}