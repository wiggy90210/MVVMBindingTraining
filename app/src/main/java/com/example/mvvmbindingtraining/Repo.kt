package com.example.mvvmbindingtraining

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*


object Repo {

    private val englishWords: List<String> = listOf(
        "Big", "Small", "Tall", "Interesting", "Happy", "Apple",
        "Funny", "Easy", "Green", "Buy", "Salary"
    )

    private val polishWords: List<String> = listOf(
        "Duży", "Mały", "Wysoki", "Interesujący", "Szczęśliwy", "Jabłko",
        "Zabawny", "Łatwy", "Zielony", "Kupić", "Wypłata"
    )

    private val _textColor = MutableLiveData<Int>()
    val textColor: LiveData<Int>
        get() = _textColor

    private val _currentRandomWord = MutableLiveData<String>()
    val currentRandomWord: LiveData<String>
        get() = _currentRandomWord

    private val _currentRandomAnswer = MutableLiveData<String>()
    val currentRandomAnswer: LiveData<String>
        get() = _currentRandomAnswer

    init {
        _currentRandomWord.value = englishWords.first()
        _currentRandomAnswer.value = polishWords.first()
        _textColor.value = R.color.black
    }

    fun getRandomWord(): String {
        val random = Random()
        val rand = random.nextInt(englishWords.size)
        _currentRandomAnswer.value = polishWords[rand]
        return englishWords[random.nextInt(englishWords.size)]
    }

    fun changeColor(correct: Boolean) {
        if (correct) {
            _textColor.value = R.color.green
        } else {
            _textColor.value = R.color.red
        }
    }

    fun resetColor() {
        _textColor.value = R.color.black
    }

    fun changeCurrentRandomWord() {
        _currentRandomWord.value = getRandomWord()
    }
}