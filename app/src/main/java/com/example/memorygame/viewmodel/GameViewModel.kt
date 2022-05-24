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
        Card(1, R.drawable.bulbasaur),
        Card(2, R.drawable.caterpie),
        Card(3, R.drawable.clefairy),
        Card(4, R.drawable.pidgey),
        Card(5, R.drawable.pikachu),
        Card(6, R.drawable.charmander),
        Card(7, R.drawable.diglett),
        Card(8, R.drawable.ekans),
        Card(9, R.drawable.jigglypuff),
        Card(10, R.drawable.nidoran),
        Card(11, R.drawable.oddish),
        Card(12, R.drawable.rattata),
        Card(13, R.drawable.squirtle),
        Card(14, R.drawable.sandshrew),
        Card(15, R.drawable.zubat),
    )

    private var _cards: MutableState<MutableList<Card>> = mutableStateOf(mutableListOf())
    var cards: MutableState<MutableList<Card>> = _cards

    var columns by mutableStateOf(0)
    var parCount by mutableStateOf(0)
    var movement by mutableStateOf(0)

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

    fun getIndex1(index: Int){
        _choiceIndex1.value = index
    }

    fun getIndex2(index: Int){
        _choiceIndex2.value = index
        viewModelScope.launch {
            delay(1000)
            compareCard()
        }
        movement++
    }

    fun updateColumns(number: Int){
        columns = number
    }

    fun updateCards(){
        getRandomList()
    }

    fun updateCountPar(countPar: Int){
        parCount = countPar
    }

    private fun compareCard(){
        match.value = cards.value[choiceIndex1.value].id == cards.value[choiceIndex2.value].id
        if(match.value){
            list.value.add(cards.value[choiceIndex2.value].id)
            choiceIndex1.value = -1
            choiceIndex2.value = -1
            parCountDown()
        }else{
            choiceIndex1.value = -1
            choiceIndex2.value = -1
        }
    }

    private fun parCountDown(){
        parCount--
        if(parCount == 0){
            showAlert()
        }
    }

    fun showAlert(){
        openDialog.value = true
    }

    fun resetGame(){
        parCount = cards.value.size / 2
        choiceIndex1.value = -1
        choiceIndex2.value = -1
        time.value = 60
        movement = 0
        clicks.value = 0
        match.value = false
        list.value = mutableListOf()
    }

    fun getRandomList(){
        cards.value = mutableListOf()
        for(x in 0 until parCount){
            var randomIndex = Random.nextInt(allCards.size)
            var randomElement = allCards[randomIndex]

            while(cards.value.contains(randomElement)){
                randomIndex = Random.nextInt(allCards.size)
                randomElement = allCards[randomIndex]
            }

            for(y in 0..1){
                cards.value.add(randomElement)
            }
        }
        cards.value.shuffle()
    }
}