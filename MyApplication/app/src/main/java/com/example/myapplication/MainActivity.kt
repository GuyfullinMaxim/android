package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private val APP_PREFERENCES_COUNTER = "counter"
    private var counter: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("settings",
            Context.MODE_PRIVATE)

        // Создание поля текста и кнопок
        val textView: TextView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)
        val buttonReset: Button = findViewById(R.id.buttonReset)

        // Кнопка "Добавить"
        button.setOnClickListener {
            counter = ++counter
            textView.text = "$counter"
        }

        // Кнопка "Сброс"
        buttonReset.setOnClickListener {
            counter = 0
            textView.text = "$counter"
        }
    }

    override fun onPause() {
        super.onPause()

        // Запоминаем данные
        val editor = prefs.edit();
        editor.putInt(APP_PREFERENCES_COUNTER, counter).apply()
    }

    override fun onResume() {
        super.onResume()

        if(prefs.contains(APP_PREFERENCES_COUNTER)){
            // Получаем число из настроек
            counter = prefs.getInt(APP_PREFERENCES_COUNTER, 0)
            // Выводим на экран данные из настроек
            val textView: TextView = findViewById(R.id.textView)
            textView.text = "$counter"
        }
    }
}