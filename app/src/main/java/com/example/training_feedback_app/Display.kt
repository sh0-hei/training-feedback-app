package com.example.training_feedback_app


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

//カスタムの View クラス（＝独自の描画エリア）**を定義したもの
//MainActivity から画像（Bitmap）を受け取り、それを画面に描画する役割を担っています

//Androidのviewを継承して作ったクラス・通常のボタンやテキストではなく、自分で画像などを直接描画出来るカスタムビュー
class Display @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null//XMLから使えるようにするためのおまじない
) : View(context, attrs) {

    //フィールド変数
    private val srcRect = Rect(0, 0, 480, 640)//元画像のサイズ,始点は左上(0,0)
    private var disRect: Rect? = null//実際の画面に合わせてスケーリングするための描画先のサイズ
    private var b: Bitmap? = null//表示するBitmapの実体

    //3.画像を受け取る関数・MainActivityから呼び出されて、描画するBitmapをセットする
    fun getBitmap(bitmap: Bitmap) {//bitmapは生データと姿勢のランドマークが描かれた画像
        b = bitmap
        srcRect.set(0,0,bitmap.width,bitmap.height)//+
        invalidate()//再描画をリクエスト//+
    }

    //描画処理・invalidate
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //invalidate()//毎回再描画
        disRect = Rect(0, 0, right, bottom)
        b?.let {
            canvas.drawBitmap(it, srcRect, disRect!!, null)
        }
    }
}
