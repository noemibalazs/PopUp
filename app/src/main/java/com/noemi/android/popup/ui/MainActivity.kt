package com.noemi.android.popup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.noemi.android.popup.R
import com.noemi.android.popup.adapter.Pixabay
import com.noemi.android.popup.adapter.PixabayAdapter
import com.noemi.android.popup.app.App
import com.noemi.android.popup.util.showDialogFragment
import com.noemi.android.popup.viewModel.PixabayViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: PixabayViewModel

    private val adapter: PixabayAdapter by lazy {
        PixabayAdapter(pixabay)
    }

    private val pixabay: Pixabay = {
        showDialogFragment(PixabayFragment().apply {
            isCancelable = true
            arguments = Bundle().apply {
                putParcelable(EXTRA_ARTWORK, it)
            }
        }, DIALOG_TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.componemt.inject(this)
        setContentView(R.layout.activity_main)

        setUpRV()
        initObserver()
    }

    private fun setUpRV() {
        rvPicsum.adapter = adapter
    }

    private fun initObserver() {
        viewModel.getArtWorks()

        viewModel.artWorkList.observe(this, {
            adapter.submitList(it)
        })

        viewModel.progressEvent.observe(this, {
            progress.isVisible = it
        })

        viewModel.errorEvent.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    companion object {
        const val DIALOG_TAG = "dialog_tag"
        const val EXTRA_ARTWORK = "extra_artwork"
    }
}