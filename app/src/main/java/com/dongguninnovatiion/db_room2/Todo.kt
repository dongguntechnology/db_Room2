package com.dongguninnovatiion.db_room2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class Todo(
    var title: String,
)  {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}