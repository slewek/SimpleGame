package com.example.simplegame

import android.content.Context
import android.graphics.Canvas
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context : Context) : SurfaceView(context) {
  fun addCallback(callback: SurfaceHolder.Callback) {
    holder.addCallback(callback)
  }
  fun drawInHolder(call: (Canvas) -> Unit) {
    var c: Canvas? = null
    try {
      c = holder.lockCanvas()
      call(c)
    } finally {
      c?.let { holder.unlockCanvasAndPost(it) }
    }
  }
}
