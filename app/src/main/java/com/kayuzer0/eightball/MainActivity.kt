package com.kayuzer0.eightball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val askButton = findViewById<Button>(R.id.ask_button)
        val answerTextView = findViewById<TextView>(R.id.answerTextView)

        askButton.setOnClickListener {
            val answers = listOf("It is certain.", "It is decidedly so.", "Without a doubt.", "Yes, definitely.", "You may rely on it.", "As I see it, yes.", "Most likely.", "Outlook good.", "Yes.", "Sings point to yes.", "Reply hazy, try again.", "Ask again later.", "Better not tell you now.", "Cannot predict now.", "Concentrate and ask again.", "Don't count on it.", "My reply is no.", "My sources say no.", "Outlook not so good.", "Very doubtful.")
            val randomIndex = Random.nextInt(answers.size)
            val randomAnswer = answers[randomIndex]
            answerTextView.text = randomAnswer
        }
    }
}