package com.noemi.android.popup.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.noemi.android.popup.R
import com.noemi.android.popup.api.model.Artwork
import com.noemi.android.popup.app.App
import com.noemi.android.popup.viewModel.PixabayViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pixabay_fragment.*
import javax.inject.Inject

class PixabayFragment : DialogFragment() {

    @Inject
    lateinit var viewModel: PixabayViewModel

    private val marginVertical by lazy {
        resources.getDimensionPixelSize(R.dimen.offset_huge)
    }

    private val marginHorizontal by lazy {
        resources.getDimensionPixelSize(R.dimen.offset_large)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CustomDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.instance.componemt.inject(this)
        return inflater.inflate(R.layout.pixabay_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSimilarArtWorks()
        val artwork = arguments?.getParcelable<Artwork>(MainActivity.EXTRA_ARTWORK)

        populateUI(artwork)
        initObservers()
    }

    private fun populateUI(artWork: Artwork?) {
        artWork?.let {
            Picasso.get().load(it.url).into(ivMainArtWork)
            tvArtworkTags.text = it.user
        }
    }

    private fun initObservers() {
        viewModel.similarArtWorks.observe(viewLifecycleOwner, {
            populateImageViews(it)
        })

        viewModel.progressEvent.observe(viewLifecycleOwner, {
            progress.isVisible = it
        })

        viewModel.errorEvent.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    private fun populateImageViews(urls: ArrayList<String>) {
        val views = listOf(ivFirst, ivSecond, ivThird)
        urls.forEachIndexed { index, url ->
            Picasso.get().load(url).into(views[index])
        }
    }

    override fun onStart() {
        super.onStart()
        val displayRect = Rect()
        dialog?.window?.decorView?.getWindowVisibleDisplayFrame(displayRect)
        val screenWidth = displayRect.width()
        val screenHeight = displayRect.height()
        dialog?.window?.setLayout(
            screenWidth - marginHorizontal * 2,
            screenHeight - marginVertical * 2
        )
    }
}