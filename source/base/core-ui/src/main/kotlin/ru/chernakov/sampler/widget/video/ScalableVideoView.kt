package ru.chernakov.sampler.widget.video

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.media.MediaPlayer.OnVideoSizeChangedListener
import android.net.Uri
import android.util.AttributeSet
import android.view.Surface
import android.view.TextureView
import android.view.TextureView.SurfaceTextureListener
import androidx.annotation.RawRes
import java.io.FileDescriptor
import java.io.IOException
import ru.chernakov.sampler.R

open class ScalableVideoView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : TextureView(context, attrs, defStyle), SurfaceTextureListener, OnVideoSizeChangedListener {

    private val mediaPlayer: MediaPlayer by lazy { initPlayer() }

    var scalableType = ScalableType.NONE
        set(value) {
            field = value
            scaleVideoSize(videoWidth, videoHeight)
        }

    val currentPosition: Int
        get() = mediaPlayer.currentPosition

    val duration: Int
        get() = mediaPlayer.duration

    val videoHeight: Int
        get() = mediaPlayer.videoHeight

    val videoWidth: Int
        get() = mediaPlayer.videoWidth

    var isLooping: Boolean
        get() = mediaPlayer.isLooping
        set(looping) {
            mediaPlayer.isLooping = looping
        }

    val isPlaying: Boolean
        get() = mediaPlayer.isPlaying

    init {
        attrs?.let { applyAttributes(it) }
    }

    private fun applyAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScalableVideoView, 0, 0)
        try {
            val scaleType = typedArray.getInt(R.styleable.ScalableVideoView_scalableType, ScalableType.NONE.ordinal)
            scalableType = ScalableType.values()[scaleType]
        } finally {
            typedArray.recycle()
        }
    }

    override fun onSurfaceTextureAvailable(surfaceTexture: SurfaceTexture, width: Int, height: Int) {
        mediaPlayer.setSurface(Surface(surfaceTexture))
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
        // do nothing
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        return false
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
        // do nothing
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (isPlaying) {
            stop()
        }

        release()
    }

    override fun onVideoSizeChanged(mp: MediaPlayer, width: Int, height: Int) {
        scaleVideoSize(width, height)
    }

    private fun scaleVideoSize(videoWidth: Int, videoHeight: Int) {
        if (videoWidth == 0 || videoHeight == 0) {
            return
        }
        val viewSize = Size(width, height)
        val videoSize = Size(videoWidth, videoHeight)
        val scaleManager = ScaleManager(viewSize, videoSize)
        val matrix = scaleManager.getScaleMatrix(scalableType)
        matrix?.let { setTransform(it) }
    }

    private fun initPlayer(): MediaPlayer {
        return MediaPlayer().apply {
            setOnVideoSizeChangedListener(this@ScalableVideoView)
            surfaceTextureListener = this@ScalableVideoView
        }
    }

    @Throws(IOException::class)
    fun setRawData(@RawRes id: Int) {
        val afd = resources.openRawResourceFd(id)
        setDataSource(afd)
    }

    @Throws(IOException::class)
    fun setAssetData(assetName: String) {
        val manager = context.assets
        val afd = manager.openFd(assetName)
        setDataSource(afd)
    }

    @Throws(IOException::class)
    private fun setDataSource(afd: AssetFileDescriptor) {
        setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        afd.close()
    }

    @Throws(IOException::class)
    fun setDataSource(path: String) {
        reset()
        mediaPlayer.setDataSource(path)
    }

    @Throws(IOException::class)
    fun setDataSource(context: Context, uri: Uri, headers: Map<String?, String?>?) {
        reset()
        mediaPlayer.setDataSource(context, uri, headers)
    }

    @Throws(IOException::class)
    fun setDataSource(context: Context, uri: Uri) {
        reset()
        mediaPlayer.setDataSource(context, uri)
    }

    @Throws(IOException::class)
    fun setDataSource(fd: FileDescriptor, offset: Long, length: Long) {
        reset()
        mediaPlayer.setDataSource(fd, offset, length)
    }

    @Throws(IOException::class)
    fun setDataSource(fd: FileDescriptor) {
        reset()
        mediaPlayer.setDataSource(fd)
    }

    @JvmOverloads
    @Throws(IOException::class, IllegalStateException::class)
    fun prepare(listener: OnPreparedListener? = null) {
        mediaPlayer.apply {
            setOnPreparedListener(listener)
            prepare()
        }
    }

    @JvmOverloads
    @Throws(IllegalStateException::class)
    fun prepareAsync(listener: OnPreparedListener? = null) {
        mediaPlayer.apply {
            setOnPreparedListener(listener)
            prepareAsync()
        }
    }

    fun setOnErrorListener(listener: MediaPlayer.OnErrorListener?) {
        mediaPlayer.setOnErrorListener(listener)
    }

    fun setOnCompletionListener(listener: OnCompletionListener?) {
        mediaPlayer.setOnCompletionListener(listener)
    }

    fun setOnInfoListener(listener: MediaPlayer.OnInfoListener?) {
        mediaPlayer.setOnInfoListener(listener)
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun seekTo(msec: Int) {
        mediaPlayer.seekTo(msec)
    }

    fun setVolume(leftVolume: Float, rightVolume: Float) {
        mediaPlayer.setVolume(leftVolume, rightVolume)
    }

    fun start() {
        mediaPlayer.start()
    }

    fun stop() {
        mediaPlayer.stop()
    }

    fun reset() {
        mediaPlayer.reset()
    }

    fun release() {
        reset()
        mediaPlayer.release()
    }
}