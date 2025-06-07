package com.example.training_feedback_app


import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions
import java.util.concurrent.ExecutionException
// ★PoseGraphicRendererの「完全な住所」をここに記述してインポートする
import com.example.training_feedback_app.camera.PoseGraphicRenderer
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage

@OptIn(androidx.camera.core.ExperimentalGetImage::class)
class TrainerActivity : AppCompatActivity() {

    // --- MainActivityが管理するプロパティ ---
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private var cameraProvider: ProcessCameraProvider? = null
    private val PERMISSION_REQUESTS: Int = 1
    private lateinit var previewView: PreviewView
    private lateinit var display: Display
    private var lensFacing: Int = CameraSelector.LENS_FACING_FRONT

    // ★描画処理クラスのインスタンスを作成
    private val poseGraphicRenderer = PoseGraphicRenderer()

    private val poseDetector = PoseDetection.getClient(
        PoseDetectorOptions.Builder()
            .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
            .build()
    )

    // カメラからの画像バッファなど
    private val bitmapArrayList = ArrayList<Bitmap>()
    private val bitmap4DisplayArrayList = ArrayList<Bitmap>()
    private val poseArrayList = ArrayList<Pose>()
    private lateinit var bitmap4Save: Bitmap
    private var isRunning: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer)
        previewView = findViewById(R.id.previewView)
        display = findViewById(R.id.displayOverlay)

        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            try {
                cameraProvider = cameraProviderFuture.get()
                bindPreview()
            } catch (e: Exception) { /* エラー処理 */ }
        }, ContextCompat.getMainExecutor(this))

        if (!allPermissionsGranted()) {
            getRuntimePermissions()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private val runMlkit = Runnable {
        if (bitmapArrayList.isNotEmpty()) {
            poseDetector.process(InputImage.fromBitmap(bitmapArrayList[0], 0))
                .addOnSuccessListener { pose -> poseArrayList.add(pose) }
                .addOnFailureListener { /* エラー処理 */ }
        }
    }

    private fun bindPreview() {
        val cameraProvider = cameraProvider ?: return
        cameraProvider.unbindAll()

        val preview = Preview.Builder().build()
        preview.setSurfaceProvider(previewView.surfaceProvider)
        val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
        val imageAnalysis = ImageAnalysis.Builder()
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()

        imageAnalysis.setAnalyzer(ActivityCompat.getMainExecutor(this)) { imageProxy ->
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            val image = imageProxy.image ?: return@setAnalyzer

            // Bitmap変換処理
            val byteBuffer = image.planes[0].buffer
            byteBuffer.rewind()
            val bitmap = Bitmap.createBitmap(imageProxy.width, imageProxy.height, Bitmap.Config.ARGB_8888)
            bitmap.copyPixelsFromBuffer(byteBuffer)
            imageProxy.close()

            val matrix = Matrix().apply {
                postRotate(rotationDegrees.toFloat())
                if (lensFacing == CameraSelector.LENS_FACING_FRONT) {
                    postScale(-1f, 1f)
                }
            }
            val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            bitmapArrayList.add(rotatedBitmap)

            if (poseArrayList.isNotEmpty()) {
                val canvas = Canvas(bitmapArrayList[0])
                val pose = poseArrayList[0]

                // ★描画処理をすべてPoseGraphicRendererに任せる
                poseGraphicRenderer.draw(canvas, pose, lensFacing)

                bitmap4DisplayArrayList.clear()
                bitmap4DisplayArrayList.add(bitmapArrayList[0])
                bitmap4Save = bitmapArrayList.last()
                bitmapArrayList.clear()
                bitmapArrayList.add(bitmap4Save)
                poseArrayList.clear()
                isRunning = false
            }

            if (poseArrayList.isEmpty() && bitmapArrayList.isNotEmpty() && !isRunning) {
                runMlkit.run()
                isRunning = true
            }

            if (bitmap4DisplayArrayList.isNotEmpty()) {
                display.getBitmap(bitmap4DisplayArrayList[0])
            }
        }
        cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, imageAnalysis, preview)
    }

    // パーミッション関連の関数（変更なし）
    private fun getRequiredPermissions(): Array<String> {
        return try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
            info.requestedPermissions ?: arrayOf()
        } catch (e: Exception) {
            arrayOf()
        }
    }

    private fun allPermissionsGranted(): Boolean {
        return getRequiredPermissions().all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun getRuntimePermissions() {
        val allNeededPermissions = mutableListOf<String>()
        for (permission in getRequiredPermissions()) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                allNeededPermissions.add(permission)
            }
        }

        if (allNeededPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                allNeededPermissions.toTypedArray(),
                PERMISSION_REQUESTS
            )
        }
    }
}