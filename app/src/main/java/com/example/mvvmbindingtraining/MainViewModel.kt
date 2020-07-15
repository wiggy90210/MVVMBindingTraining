package com.example.mvvmbindingtraining

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val currentRandomWord: LiveData<String>
        get() = Repo.currentRandomWord
    val currentRandomAnswer: LiveData<String>
        get() = Repo.currentRandomAnswer
    val textColor: LiveData<Int>
        get() = Repo.textColor

    fun onChangeRandomWord() = Repo.changeCurrentRandomWord()

    @Bindable
    val editTextContent = MutableLiveData<String>()

    private val _displayEditTextContent = MutableLiveData<String>()
    val displayedEditTextContent: LiveData<String>
        get() = _displayEditTextContent

    fun onDisplayEditTextContentClick() {
        _displayEditTextContent.value = editTextContent.value
        checkAnswer()
    }

    fun checkAnswer() {
        Repo.changeColor(displayedEditTextContent == currentRandomAnswer)
    }
    fun onSelectRandomEditText() {

    }
}