package com.dongguninnovatiion.db_room2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Todo::class],
    version = 1,
)

public abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao() : TodoDao
}