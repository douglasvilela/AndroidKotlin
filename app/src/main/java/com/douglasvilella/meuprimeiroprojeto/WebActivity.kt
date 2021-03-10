package com.douglasvilella.meuprimeiroprojeto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.douglasvilella.meuprimeiroprojeto.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Habilitando Javascript no web view
        binding.wbvWeb.settings.javaScriptEnabled = true

        //carregando um endereço no wbvWeb

        binding.wbvWeb.loadUrl("https://br.cellep.com/estacaohack")

        //definindo webview como cliente web padrao

        binding.wbvWeb.webViewClient = WebViewClient()

    }
}