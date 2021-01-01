package com.myanmaritc.news.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.myanmaritc.news.R
import com.myanmaritc.news.model.ArticlesItem
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    lateinit var article : ArticlesItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView.settings.apply {
            loadsImagesAutomatically = true
            javaScriptEnabled= true
            domStorageEnabled = true
            supportZoom()
            displayZoomControls = true

        }
        webView.webViewClient = WebViewClient()

        article = args.article

        webView.loadUrl(article.url.toString())
    }


}