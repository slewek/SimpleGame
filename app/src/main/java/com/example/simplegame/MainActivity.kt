package com.example.simplegame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import com.example.game.GameControllerInterface
import com.example.game.GameState
import com.example.game.GameStateAdapter
import com.example.game.Sprite
import java.util.*

class MainActivity : AppCompatActivity(), GameControllerInterface {
  private lateinit var gameView: GameView
  private var loopingThread: LoopingThread? = null

  private lateinit var picture: Bitmap
  private val gameState = GameState()
  private lateinit var gameStateAdapter: GameStateAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    gameView = GameView(this)
    gameView.addCallback(this)

    setContentView(gameView)

    picture = BitmapFactory.decodeResource(resources, R.drawable.good1)
    gameStateAdapter = GameStateAdapter(resources)


  }

  @Synchronized
  override fun clickHere(x: Float, y: Float) {
   // gameState.killSprite(x,y)
    gameState.setGoodSpeed(x,y, gameView.width, gameView.height)
  }

  override fun surfaceDestroyed(holder: SurfaceHolder?) {
    loopingThread?.stopLoop()
  }

  @Synchronized
  override fun surfaceCreated(holder: SurfaceHolder?) {
    gameState.initGame(resources,
      gameView.width, gameView.height)
    loopingThread = LoopingThread(this::loop)
    loopingThread?.startLoop()
  }

  @Synchronized
  private fun loop() {
    gameState.update(gameView.width, gameView.height)
    gameView.drawInHolder {
      it.drawColor(Color.BLACK)
      gameStateAdapter.draw(it, gameState)
    }
  }

}
