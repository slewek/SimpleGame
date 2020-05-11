package com.example.game

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.example.simplegame.R
import java.util.*

class GameState {
  val temps = mutableListOf<TempSprite>()
  val sprites = mutableListOf<Sprite>()
  var endState = EndState.NO

  private fun createSprite(
    resources: Resources,
    maxWidth: Int,
    maxHeight: Int,
    resourceId: Int,
    good : Boolean
  ) : Sprite {
    val bmp = BitmapFactory.decodeResource(resources, resourceId)
    val rnd = Random()
    val x = rnd.nextInt(maxWidth - bmp.width)
    val y = rnd.nextInt(maxHeight - bmp.width)
    val xSpeed = rnd.nextInt(2 * Sprite.MAX_SPEED) - Sprite.MAX_SPEED
    val ySpeed = rnd.nextInt(2 * Sprite.MAX_SPEED) - Sprite.MAX_SPEED
    return Sprite(bmp, x, y, xSpeed, ySpeed, good)
  }

  fun initGame(resources: Resources, maxWidth: Int, maxHeight: Int) {
    endState = EndState.NO
    temps.clear()
    sprites.clear()
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad1, false))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad2, false))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad3, false))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad4, false))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad5, false))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad6, false))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good1, true))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good2, true))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good3, true))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good4, true))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good5, true))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good6, true))
  }

  fun update(maxWidth: Int, maxHeight: Int) {
    if(isGameOver()) return
    for (sprite in sprites) {
      sprite.update(maxWidth, maxHeight)
    }
    for (i in temps.lastIndex downTo 0) {
      temps[i].update()
      if (temps[i].canBeRemoved())
        temps.removeAt(i)
    }
    calcEnd()
  }

  fun killSprite(x: Float, y: Float) {
    if (isGameOver()) return
    for (i in sprites.lastIndex downTo 0) {
      val sprite = sprites[i]
      if (sprite.isCollision(x, y)) {
        sprites.remove(sprite)
        temps.add(TempSprite(x,y))
        break
      }
    }
  }

  private fun calcEnd() {
    val leftGood = sprites.filter { it.good }.size
    val leftBad = sprites.size - leftGood
    if (leftGood == 0) {
      endState = EndState.BAD_WINS
    } else if (leftBad == 0) {
      endState = EndState.GOOD_WINS
    }
  }
  fun isGameOver(): Boolean {
    return endState != EndState.NO
  }
}
