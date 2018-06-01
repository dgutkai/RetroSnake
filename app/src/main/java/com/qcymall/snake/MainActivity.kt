package com.qcymall.snake

import android.opengl.GLSurfaceView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import java.util.*

class MainActivity : AppCompatActivity() {
    private val glRenderer: GLRenderer? = null
    private lateinit var snake: SnakeSpace

    private var mTimer: Timer? = null
    private var mTimerTask: TimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        snake = findViewById(R.id.snake_view)
        startTimer()
//        /* 以下是重点 */
//        val demoGlv = findViewById<GLSurfaceView>(R.id.glv_main_demo)
//        // 设置OpenGL版本(一定要设置)
//        demoGlv.setEGLContextClientVersion(2)
//        // 设置渲染器(后面会着重讲这个渲染器的类)
//        demoGlv.setRenderer(MyRenderer())
//        // 设置渲染模式为写的时候更新(会以60fps的速度刷新)
//        demoGlv.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY)
//        /* 重点结束 */

//        /* 以下是重点 */
//        val demoGlv = findViewById<SurfaceView>(R.id.glv_main_demo)
//
//        glRenderer = GLRenderer()
//        glRenderer!!.start()
//
//        demoGlv.holder.addCallback(object : SurfaceHolder.Callback{
//            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
//                glRenderer!!.render(p0!!.getSurface(), p2, p3);
//            }
//
//            override fun surfaceDestroyed(p0: SurfaceHolder?) {
//            }
//
//            override fun surfaceCreated(p0: SurfaceHolder?) {
//            }
//
//        })
    }

    private fun startTimer(){
        stopTimer()
        mTimer = Timer()
        mTimerTask = object : TimerTask(){
            override fun run() {
                snake.move()
            }

        }
        mTimer!!.schedule(mTimerTask, 1000, 100)
    }

    private fun stopTimer(){
        if (mTimer != null){
            mTimer!!.cancel()
            mTimer = null
        }
        if (mTimerTask != null){
            mTimerTask!!.cancel()
            mTimerTask = null
        }
    }
    public fun upMove(view: View){
        snake.move(SnakeSpace.DIRECTION_UP)
    }
    public fun downMove(view: View){
        snake.move(SnakeSpace.DIRECTION_DOWN)
    }
    public fun leftMove(view: View){
        snake.move(SnakeSpace.DIRECTION_LEFT)
    }
    public fun rightMove(view: View){
        snake.move(SnakeSpace.DIRECTION_RIGHT)
    }

//override fun onDestroy() {
//    glRenderer!!.release();
//    glRenderer = null;
//    super.onDestroy()
//}
}
