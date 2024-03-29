package ru.chernakov.sampler.swiper.presentation

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.core.ui.extension.doPostDelayed
import ru.chernakov.sampler.core.ui.extension.findView
import ru.chernakov.sampler.core.ui.extension.getColorExt
import ru.chernakov.sampler.core.ui.extension.observeOnCreated
import ru.chernakov.sampler.core.ui.extension.runFadeInAnimation
import ru.chernakov.sampler.core.ui.extension.unsafeLazy
import ru.chernakov.sampler.core.ui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.swiper.R
import ru.chernakov.sampler.swiper.presentation.model.SwiperModel
import ru.chernakov.sampler.widget.appbar.Toolbar
import ru.chernakov.sampler.widget.container.FrameContainer
import ru.chernakov.sampler.widget.container.MotionContainer
import ru.chernakov.sampler.widget.image.ImageView
import ru.chernakov.sampler.widget.text.TextView
import ru.chernakov.sampler.widget.video.ScalableType
import ru.chernakov.sampler.widget.video.ScalableVideoView

class SwiperFragment : BaseFragment(R.layout.fragment_swiper) {
    override val viewModel: SwiperViewModel by viewModel()

    private val swiperToolbar by findView<Toolbar>(R.id.swiper_toolbar)

    private val swiperMotionContainer by findView<MotionContainer>(R.id.swiper_motion_container)

    private val swiperTopCardContainer by findView<FrameContainer>(R.id.swiper_top_card_view_content)
    private val swiperBottomCardContainer by findView<FrameContainer>(R.id.swiper_bottom_card_view_content)

    private val swiperTopCardImage by findView<ImageView>(R.id.swiper_top_card_image)
    private val swiperBottomCardImage by findView<ImageView>(R.id.swiper_bottom_card_image)

    private val swiperTopCardVideoView by findView<ScalableVideoView>(R.id.swiper_top_card_web_view)
    private val swiperTopCardName by findView<TextView>(R.id.swiper_top_card_view_name)
    private val swiperBottomCardName by findView<TextView>(R.id.swiper_bottom_card_view_name)

    private val transitionAdapter by unsafeLazy { createTransitionAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cardsState.observeOnCreated(lifecycleScope) {
            bindCards(it)
        }

        swiperToolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        swiperTopCardName.setTextColor(view.context.getColorExt(R.color.white))
        swiperBottomCardName.setTextColor(view.context.getColorExt(R.color.white))

        setupListeners()
    }

    private fun setupListeners() {
        swiperMotionContainer.setTransitionListener(transitionAdapter)
    }

    private fun bindCards(model: SwiperModel) {
        doPostDelayed(BOTTOM_CARD_BACKGROUND_CHANGE_DELAY) {
            swiperBottomCardImage.setImageResource(model.bottomCard.imageRes)
            swiperBottomCardName.setText(model.bottomCard.nameRes)
        }
        swiperTopCardImage.setImageResource(model.topCard.imageRes)
        swiperTopCardName.setText(model.topCard.nameRes)
        swiperTopCardVideoView.apply {
            setDataSource(context.getString(model.topCard.loopUrl))
            prepareAsync { player ->
                player.start()
                isLooping = true
                scalableType = ScalableType.CENTER_CROP
                doPostDelayed(VIDEO_START_DELAY) {
                    swiperTopCardVideoView.isVisible = true
                    swiperTopCardContainer.runFadeInAnimation()
                }
            }
        }
        swiperMotionContainer.progress = 0F
    }

    private fun createTransitionAdapter(): TransitionAdapter {
        return object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                val isEndState = currentId == motionLayout.endState
                val isCorrectCurrentId = currentId == R.id.offScreenLeft || currentId == R.id.offScreenRight

                when {
                    isEndState && isCorrectCurrentId -> {
                        motionLayout.progress = 0F
                        motionLayout.setTransition(R.id.middle, R.id.right)
                        viewModel.swipe()
                        swiperTopCardVideoView.isVisible = false
                    }
                }
            }
        }
    }

    companion object {
        private const val BOTTOM_CARD_BACKGROUND_CHANGE_DELAY = 100L
        private const val VIDEO_START_DELAY = 300L
    }
}