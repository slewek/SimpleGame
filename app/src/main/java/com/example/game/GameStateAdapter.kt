package com.example.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect

class GameStateAdapter {
  fun drawSprite(canvas: Canvas, sprite: Sprite) {
    val srcX = sprite.currentFrame * sprite.width
    val srcY = Sprite.DIRECTION_TO_ANIMATION_MAP[sprite.getDirection()] * sprite.height
    val src = Rect(
      srcX,
      srcY,
      srcX + sprite.width,
      srcY + sprite.height
    )
    val dst = Rect(
      sprite.x,
      sprite.y,
      sprite.x + sprite.width,
      sprite.y + sprite.height
    )
    canvas.drawBitmap(sprite.bmp, src, dst, null)
  }

  fun draw(canvas: Canvas, gameState: GameState) {
    canvas.drawColor(Color.BLACK)
    for (sprite in gameState.sprites) {
      drawSprite(canvas, sprite)
    }
  }
}
