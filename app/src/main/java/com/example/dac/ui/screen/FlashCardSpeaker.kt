package com.example.dac.ui.screen

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class FlashCardSpeaker(context: Context) {
    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
            }
        }
    }

    fun speak(word: String) {
        tts?.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun shutdown() {
        tts?.shutdown()
    }
}