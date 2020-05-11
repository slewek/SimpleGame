package com.example.simplegame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import com.example.game.GameStateAdapter
import com.example.game.Sprite
import java.util.*

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback {
  private lateinit var gameView: GameView
  private var loopingThread: LoopingThread? = null

  private lateinit var picture: Bitmap
  private lateinit var sprite: Sprite

  private lateinit var gameStateAdapter: GameStateAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    picture = BitmapFactory.decodeResource(resources, R.drawable.blood1)
    gameStateAdapter = GameStateAdapter()

    gameView = GameView(this)
    gameView.addCallback(this)

    setContentView(gameView)
  }

  override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
  }

  override fun surfaceDestroyed(holder: SurfaceHolder?) {
    loopingThread?.stopLoop()
  }

  override fun surfaceCreated(holder: SurfaceHolder?) {
    loopingThread = LoopingThread(this::loop)
    loopingThread?.startLoop()
  }

  private fun loop() {
    sprite.update(gameView.width, gameView.height)
    gameView.drawInHolder {
      it.drawColor(Color.BLACK)
      gameStateAdapter.drawSprite(it, sprite)
    }
  }

}
