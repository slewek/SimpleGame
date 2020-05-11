package com.example.game

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.example.simplegame.R
import java.util.*

class GameState {
  val sprites = mutableListOf<Sprite>()

  private fun createSprite(
    resources: Resources,
    maxWidth: Int,
    maxHeight: Int,
    resourceId: Int
  ) : Sprite {
    val bmp = BitmapFactory.decodeResource(resources, resourceId)
    val rnd = Random()
    val x = rnd.nextInt(maxWidth - bmp.width)
    val y = rnd.nextInt(maxHeight - bmp.width)
    val xSpeed = rnd.nextInt(2 * Sprite.MAX_SPEED) - Sprite.MAX_SPEED
    val ySpeed = rnd.nextInt(2 * Sprite.MAX_SPEED) - Sprite.MAX_SPEED
    return Sprite(bmp, x, y, xSpeed, ySpeed)
  }

  fun initGame(resources: Resources, maxWidth: Int, maxHeight: Int) {
    sprites.clear()
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad1))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad2))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad3))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad4))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad5))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.bad6))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good1))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good2))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good3))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good4))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good5))
    sprites.add(createSprite(resources, maxWidth, maxHeight, R.drawable.good6))
  }

  fun update(maxWidth: Int, maxHeight: Int) {
    for (sprite in sprites) {
      sprite.update(maxWidth, maxHeight)
    }
  }
}
