package com.binayshaw7777.knowtify.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.DictionaryDatabase

fun getDictionaryDatabase(context: Context): RoomDatabase.Builder<DictionaryDatabase> {
    val dbFile = context.getDatabasePath("dictionary.db")
    return Room.databaseBuilder<DictionaryDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
}