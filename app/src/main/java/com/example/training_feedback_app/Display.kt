package com.example.training_feedback_app

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

// カスタムの View クラス（＝独自の描画エリア）を定義したもの
// MainActivity から画像（Bitmap）を受け取り、それを画面に描画する役割を担っています

// Android の View を継承して作ったクラス。通常のボタンやテキストではなく、自分で画像などを直接描画できるカスタムビュー。
class Display @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null // XML から使えるようにするためのおまじない
) : View(context, attrs) {

    // フィールド変数
    private val srcRect = Rect(0, 0, 480, 640) // 元画像のサイズ。始点は左上(0,0)
    private var disRect: Rect? = null // 実際の画面に合わせてスケーリングするための描画先のサイズ
    private var b: Bitmap? = null // 表示する Bitmap の実体

    // 画像を受け取る関数・MainActivity から呼び出されて、描画する Bitmap をセットする
    fun getBitmap(bitmap: Bitmap) {
        b = bitmap
        srcRect.set(0, 0, bitmap.width, bitmap.height) // 画像サイズに応じて元矩形を更新
        invalidate() // 再描画をリクエスト
    }

    // 描画処理（invalidate により呼ばれる）
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        disRect = Rect(0, 0, right, bottom)
        b?.let {
            canvas.drawBitmap(it, srcRect, disRect!!, null)
        }
    }
}