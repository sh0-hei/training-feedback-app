package com.example.training_feedback_app.camera

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.camera.core.CameraSelector
import androidx.core.graphics.withRotation
import androidx.core.graphics.withScale
import androidx.core.graphics.withTranslation
import com.example.training_feedback_app.util.getAngle
import com.example.training_feedback_app.util.linearInterp
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark

/**
 * 姿勢推定結果の描画を専門に担当するクラス
 */
class PoseGraphicRenderer {

    // 描画モードの定義
    enum class DrawMode {
        WHOLE_BODY,
        LEFT_ARM,
    }
    var currentDrawMode: DrawMode = DrawMode.LEFT_ARM
        private set

    // トレーナー機能用の状態変数
    private var curlCount = 0.0
    private var armDirection = 0
    private var justCountedUp = false

    // 描画に使用するPaintオブジェクト
    private val landmarkPaint = Paint().apply { color = Color.RED; style = Paint.Style.FILL_AND_STROKE; strokeWidth = 10f }
    private val linePaint = Paint().apply { color = Color.WHITE; style = Paint.Style.STROKE; strokeWidth = 5f }
    private val textPaint = Paint().apply { color = Color.WHITE; textSize = 40f }
    private val barPaint = Paint().apply { color = Color.parseColor("#7EFFFFFF") }
    private val barFillPaint = Paint().apply { color = Color.GREEN }
    private val barFillFlashPaint = Paint().apply { color = Color.YELLOW }
    private val countBoxPaint = Paint().apply { color = Color.parseColor("#A600FF00") }
    private val countTextPaint = Paint().apply { color = Color.WHITE; textSize = 80f; textAlign = Paint.Align.CENTER }
    private val countBoxFlashPaint = Paint().apply { color = Color.YELLOW }


    /**
     * メインの描画処理。Canvas、Pose、カメラの向きを受け取って描画する。
     */
    fun draw(canvas: Canvas, pose: Pose, lensFacing: Int) {
        when (currentDrawMode) {
            DrawMode.WHOLE_BODY -> drawWholeBody(canvas, pose)
            DrawMode.LEFT_ARM -> drawLeftArmTrainer(canvas, pose, lensFacing)
        }
    }

    private fun drawWholeBody(canvas: Canvas, pose: Pose) {
        // 全身のランドマークと線を描画する
        for (landmark in pose.allPoseLandmarks) {
            canvas.drawCircle(landmark.position.x, landmark.position.y, 5f, landmarkPaint)
        }
        // ここに全身の線を描画するdrawLine(...)呼び出しを追加
    }

    private fun drawLeftArmTrainer(canvas: Canvas, pose: Pose, lensFacing: Int) {
        // カメラの向きに応じて取得するランドマークを切り替える
        val leftShoulder = pose.getPoseLandmark(if (lensFacing == CameraSelector.LENS_FACING_FRONT) PoseLandmark.RIGHT_SHOULDER else PoseLandmark.LEFT_SHOULDER)
        val leftElbow = pose.getPoseLandmark(if (lensFacing == CameraSelector.LENS_FACING_FRONT) PoseLandmark.RIGHT_ELBOW else PoseLandmark.LEFT_ELBOW)
        val leftWrist = pose.getPoseLandmark(if (lensFacing == CameraSelector.LENS_FACING_FRONT) PoseLandmark.RIGHT_WRIST else PoseLandmark.LEFT_WRIST)

        // 点と線の描画
        listOf(leftShoulder, leftElbow, leftWrist).forEach { it?.let { lm -> canvas.drawCircle(lm.position.x, lm.position.y, 5f, landmarkPaint) } }
        drawLine(canvas, leftShoulder, leftElbow, linePaint)
        drawLine(canvas, leftElbow, leftWrist, linePaint)

        // 角度計算とカウントアップ
        val leftArmAngle = getAngle(leftShoulder, leftElbow, leftWrist)
        val percentage = linearInterp(leftArmAngle, 150.0, 50.0, 0.0, 100.0).coerceIn(0.0, 100.0)
        if (percentage >= 95.0) { if (armDirection == 0) { curlCount += 0.5; armDirection = 1; justCountedUp = true } }
        if (percentage <= 5.0) { if (armDirection == 1) { curlCount += 0.5; armDirection = 0; justCountedUp = true } }

        // UIの描画
        val barY = linearInterp(percentage, 0.0, 100.0, 450.0, 100.0)
        drawGauge(canvas, barY)
        drawCountBox(canvas)
    }

    private fun drawGauge(canvas: Canvas, barY: Double) {
        val barWidth = 75f; val barMarginLeft = 10f
        val barLeft = barMarginLeft; val barTop = 100f
        val barRight = barLeft + barWidth; val barBottom = 450f
        canvas.drawRect(barLeft, barTop, barRight, barBottom, barPaint)
        val fillPaint = if (justCountedUp) barFillFlashPaint else barFillPaint
        canvas.drawRect(barLeft, barY.toFloat(), barRight, barBottom, fillPaint)
        val percentageText = String.format("%.0f %%", linearInterp(barY, 450.0, 100.0, 0.0, 100.0))
        drawTextUpright(canvas, percentageText, barLeft, barBottom + 50f, textPaint)
    }

    private fun drawCountBox(canvas: Canvas) {
        val boxWidth = 130f; val boxHeight = 100f
        val countBoxLeft = canvas.width - boxWidth; val countBoxTop = 0f
        val countBoxRight = canvas.width.toFloat(); val countBoxBottom = boxHeight
        val boxPaint = if (justCountedUp) countBoxFlashPaint else countBoxPaint
        canvas.drawRect(countBoxLeft, countBoxTop, countBoxRight, countBoxBottom, boxPaint)
        if (justCountedUp) justCountedUp = false
        val countText = String.format("%.0f", curlCount)
        val textAnchorX = countBoxLeft + (boxWidth / 2)
        val textAnchorY = countBoxTop + (boxHeight / 2) + (countTextPaint.textSize / 3)
        drawTextUpright(canvas, countText, textAnchorX, textAnchorY, countTextPaint)
    }

    private fun drawLine(canvas: Canvas, start: PoseLandmark?, end: PoseLandmark?, paint: Paint) {
        if (start != null && end != null) canvas.drawLine(start.position.x, start.position.y, end.position.x, end.position.y, paint)
    }

    private fun drawTextUpright(canvas: Canvas, text: String, x: Float, y: Float, paint: Paint) {
        canvas.withTranslation(x, y) {
            withRotation(0f) {
                withScale(x = 1f, y = 1f) { drawText(text, 0f, 0f, paint) }
            }
        }
    }
}