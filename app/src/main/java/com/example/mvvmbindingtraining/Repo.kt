package com.example.mvvmbindingtraining

import android.view.View
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

    private val _rightAnswerVisibility = MutableLiveData<Int>()
    val rightAnswerVisibility: LiveData<Int>
        get() = _rightAnswerVisibility

    private val _displayEditTextContent = MutableLiveData<String>()
    val displayEditTextContent: LiveData<String>
        get() = _displayEditTextContent

    init {
        _currentRandomWord.value = polishWords.first()
        _currentRandomAnswer.value = englishWords.first()
        _textColor.value = R.color.black
        _rightAnswerVisibility.value = View.INVISIBLE
    }



    fun changeColor(correct: Boolean) {
        if (correct) {
            _textColor.value = R.color.green
        } else {
            _textColor.value = R.color.red
        }
    }

    fun resetColor() {                      //
        _textColor.value = R.color.black
    }

    fun hideRightAnswer() {
        _rightAnswerVisibility.value = View.INVISIBLE
    }

    fun showRightAnswer() {
        _rightAnswerVisibility.value = View.VISIBLE
    }

    fun getRandomWord(): String {       //
        val random = Random()
        val rand = random.nextInt(englishWords.size)
        _currentRandomAnswer.value = englishWords[rand]
        return polishWords[rand]
    }

    fun changeCurrentRandomWord() {                     // R.id.bGetWord
        hideRightAnswer()
        _currentRandomWord.value = getRandomWord()
        resetColor()
    }

    fun setUserAnswer(editTextContent: String?) {
        _displayEditTextContent.value = editTextContent
    }

}