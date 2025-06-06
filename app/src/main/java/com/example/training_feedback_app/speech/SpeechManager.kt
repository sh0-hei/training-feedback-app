package com.example.training_feedback_app.speech

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class SpeechManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var isInitialized = false

    init {
        tts = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.JAPANESE)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("SpeechManager", "日本語がサポートされていません。")
            } else {
                isInitialized = true
            }
        } else {
            Log.e("SpeechManager", "TextToSpeech の初期化に失敗しました。")
        }
    }

    fun speak(text: String) {
        if (isInitialized) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            Log.w("SpeechManager", "TTS 未初期化のため音声出力できません： $text")
        }
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}