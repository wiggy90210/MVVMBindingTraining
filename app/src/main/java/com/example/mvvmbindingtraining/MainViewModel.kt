package com.example.mvvmbindingtraining

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(), Observable {

    val currentRandomWord: LiveData<String>
        get() = Repo.currentRandomWord
    val currentRandomAnswer: LiveData<String>
        get() = Repo.currentRandomAnswer
    val textColor: LiveData<Int>
        get() = Repo.textColor
    val rightAnswerVisibility: LiveData<Int>
        get() = Repo.rightAnswerVisibility
    val displayedEditTextContent: LiveData<String>
        get() = Repo.displayEditTextContent

    fun onChangeRandomWord() {
        clearEditText()
        Repo.changeCurrentRandomWord()
    }

    @Bindable
    val editTextContent = MutableLiveData<String>()

    fun clearEditText() {
        editTextContent.value = ""
    }


    fun onDisplayEditTextContentClick() {
        Repo.setUserAnswer(editTextContent.value)
    }

    fun checkAnswer() {
        onDisplayEditTextContentClick()     // przypisuję wartość do prywatnej zmiennej
        Repo.changeColor(displayedEditTextContent.value.equals(currentRandomAnswer.value, ignoreCase = true))
        Repo.showRightAnswer()
    }



    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry()}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }
}