package com.binayshaw7777.knowtify

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.binayshaw7777.knowtify.database.getDictionaryDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = getDictionaryDatabase(applicationContext).dictionaryDao()

        setContent {
            App(dao)
        }
    }
}