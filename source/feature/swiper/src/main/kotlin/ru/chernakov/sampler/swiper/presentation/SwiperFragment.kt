package ru.chernakov.sampler.swiper.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.coreui.extension.findView
import ru.chernakov.sampler.coreui.extension.getColorExt
import ru.chernakov.sampler.coreui.extension.observeSafe
import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.swiper.R
import ru.chernakov.sampler.swiper.presentation.model.SwiperModel
import ru.chernakov.sampler.widget.button.ImageButton
import ru.chernakov.sampler.widget.container.FrameContainer
import ru.chernakov.sampler.widget.container.MotionContainer

class SwiperFragment : BaseFragment(R.layout.fragment_swiper) {
    override val viewModel: SwiperViewModel by viewModel()

    private val swiperMotionContainer by findView<MotionContainer>(R.id.swiper_motion_container)

    private val swiperTopCard by findView<FrameContainer>(R.id.swiper_top_card)
    private val swiperBottomCard by findView<FrameContainer>(R.id.swiper_bottom_card)

    private val swiperLikeButton by findView<ImageButton>(R.id.swiper_like_button)
    private val swiperPassButton by findView<ImageButton>(R.id.swiper_pass_button)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            cardsLiveData.observeSafe(viewLifecycleOwner) { model ->
                bindCards(model)
            }
        }

        setupListeners()
    }

    private fun setupListeners() {
        swiperMotionContainer.setTransitionListener(getTransitionAdapter())

        swiperLikeButton.setOnClickListener {
            swiperMotionContainer.transitionToState(R.id.like)
        }
        swiperPassButton.setOnClickListener {
            swiperMotionContainer.transitionToState(R.id.pass)
        }
    }

    private fun bindCards(model: SwiperModel) {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                swiperBottomCard.apply {
                    setBackgroundColor(context.getColorExt(model.bottomCard.cardColor))
                }
            },
            BOTTOM_CARD_BACKGROUND_CHANGE_DELAY
        )
        swiperTopCard.apply {
            setBackgroundColor(context.getColorExt(model.topCard.cardColor))
        }
        swiperMotionContainer.progress = 0F
    }

    private fun getTransitionAdapter() = object : TransitionAdapter() {
        override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
            when (currentId) {
                R.id.offScreenPass,
                R.id.offScreenLike -> {
                    motionLayout.progress = 0F
                    motionLayout.setTransition(R.id.rest, R.id.like)
                    viewModel.swipe()
                }
            }
        }
    }

    companion object {
        private const val BOTTOM_CARD_BACKGROUND_CHANGE_DELAY = 100L
    }
}