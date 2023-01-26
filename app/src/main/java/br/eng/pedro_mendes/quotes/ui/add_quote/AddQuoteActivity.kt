package br.eng.pedro_mendes.quotes.ui.add_quote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.eng.pedro_mendes.quotes.R
import br.eng.pedro_mendes.quotes.data.Quote
import br.eng.pedro_mendes.quotes.databinding.ActivityAddQuoteBinding
import br.eng.pedro_mendes.quotes.ui.main.MainActivity

class AddQuoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddQuoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        setSaveQuoteListener()
        setCancelListener()
    }

    private fun setSaveQuoteListener() {
        binding.saveQuote.setOnClickListener {
            onSaveQuote()
        }
    }

    private fun setCancelListener() {
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun onSaveQuote() {
        binding.apply {
            val authorName = editTextAuthorName.text.toString()
            val quote = editTextQuote.text.toString()

            validateAuthorField(authorName)
            validateQuoteField(quote)

            saveIfValidFormat(authorName, quote)
        }
    }

    private fun validateAuthorField(authorName: String) {
        binding.textInputAuthorName.error = if (authorName.isEmpty()) {
            getString(R.string.author_name_error)
        } else {
            null
        }
    }

    private fun validateQuoteField(quote: String) {
        binding.textInputQuote.error = if (quote.isEmpty()) {
            getString(R.string.quote_error)
        } else {
            null
        }
    }

    private fun saveIfValidFormat(authorName: String, quote: String) {
        if (authorName.isEmpty() || quote.isEmpty()) {
            return
        }

        Intent().apply {
            putExtra(
                MainActivity.TAG_SAVE_QUOTE,
                Quote(
                    author = authorName,
                    quote = quote
                )
            )
            setResult(RESULT_OK, this)
        }

        finish()
    }
}