package com.example.training_feedback_app.util


import com.google.mlkit.vision.pose.PoseLandmark
import kotlin.math.acos
import kotlin.math.sqrt

/**
 * 3つのランドマークから角度を計算する関数
 */
fun getAngle(
    firstPoint: PoseLandmark?,
    middlePoint: PoseLandmark?,
    lastPoint: PoseLandmark?
): Double {
    if (firstPoint == null || middlePoint == null || lastPoint == null) {
        return 0.0
    }
    val vec1x = firstPoint.position.x - middlePoint.position.x
    val vec1y = firstPoint.position.y - middlePoint.position.y
    val vec2x = lastPoint.position.x - middlePoint.position.x
    val vec2y = lastPoint.position.y - middlePoint.position.y
    val dotProduct = (vec1x * vec2x + vec1y * vec2y).toDouble()
    val mag1 = sqrt((vec1x * vec1x + vec1y * vec1y).toDouble())
    val mag2 = sqrt((vec2x * vec2x + vec2y * vec2y).toDouble())
    if (mag1 == 0.0 || mag2 == 0.0) {
        return 0.0
    }
    val angleRad = acos(dotProduct / (mag1 * mag2))
    return Math.toDegrees(angleRad)
}

/**
 * 線形補間を行う関数 (numpy.interpの簡易版)
 */
fun linearInterp(x: Double, inMin: Double, inMax: Double, outMin: Double, outMax: Double): Double {
    return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin
}