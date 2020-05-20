package com.example.simplegame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View
import com.example.game.*
import java.util.*

class MainActivity : AppCompatActivity(), GameControllerInterface {
  private lateinit var gameView: GameView
  private var loopingThread: LoopingThread? = null

  private lateinit var picture: Bitmap
  private lateinit var gameState : GameState
  private lateinit var gameStateAdapter: GameStateAdapter

  private lateinit var mGestureDetector : GestureDetector
  private lateinit var soundPlayer: SoundPlayer

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    gameView = GameView(this)
    gameView.addCallback(this)

    soundPlayer = SoundPlayer(applicationContext)
    gameState = GameState(soundPlayer)

    setContentView(gameView)


    picture = BitmapFactory.decodeResource(resources, R.drawable.good1)
    gameStateAdapter = GameStateAdapter(resources)
    mGestureDetector = GestureDetector(this,
      object : GestureDetector.SimpleOnGestureListener(){
        override fun onLongPress(e: MotionEvent?) {
          if (e != null) {
            gameState.setGoodSpeed(e.x, e.y, gameView.width, gameView.height)
          }

        }

      })


  }

  @Synchronized
  override fun clickHere(x: Float, y: Float) {
    //gameState.killSprite(x,y)
    gameState.setGoodSpeed(x,y, gameView.width, gameView.height)
    gameState.throwStar()
    soundPlayer.playStarThrow()
  }


  @Synchronized
  override fun onTouchEvent(event: MotionEvent?): Boolean {
    /*  if (event != null) {
        var downTime = event.downTime
        var pressedTime = event.eventTime;
        println()
        if (event.action == MotionEvent.ACTION_DOWN) {
          gameState.throwStar()
          println("xx")
        }
      }


    return true
*/
    return mGestureDetector.onTouchEvent(event);
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
