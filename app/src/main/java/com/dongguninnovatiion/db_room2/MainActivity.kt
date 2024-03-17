package com.dongguninnovatiion.db_room2

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.dongguninnovatiion.db_room2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var db: TodoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,"todo_db"
        ).allowMainThreadQueries().build()

        startTodo()
        binding.addBtn.setOnClickListener{
            addTodo()
            refreshTodo()
        }
    }

    private fun startTodo() {
        startActivity(Intent(applicationContext,  T::class.java))
    }

    private fun addTodo() {
        val t1 = binding.todoEdit.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().insert(Todo(t1))
        }
    }

    private fun refreshTodo() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async {
                db.todoDao().getAll().toString()
            }.await()

            if(data != null) {
                binding.resultText.setText("${data.toString()}")
            }
        }
    }
}
