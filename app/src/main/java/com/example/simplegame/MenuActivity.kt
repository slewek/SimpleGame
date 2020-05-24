package com.example.simplegame

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.game.GameState
import com.example.game.GameStateAdapter
import com.example.game.SoundPlayer

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        var button = findViewById<Button>(R.id.gameButton)
        button.setOnClickListener {
            var intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }

        var rankButton = findViewById<Button>(R.id.rankButton)
        rankButton.setOnClickListener {
            var intent = Intent(applicationContext,GameRankActivity::class.java)
            startActivity(intent)
        }

    }
}