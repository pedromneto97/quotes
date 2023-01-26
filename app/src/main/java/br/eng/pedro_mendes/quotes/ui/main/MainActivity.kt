package br.eng.pedro_mendes.quotes.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.eng.pedro_mendes.quotes.R
import br.eng.pedro_mendes.quotes.data.Quote
import br.eng.pedro_mendes.quotes.databinding.ActivityMainBinding
import br.eng.pedro_mendes.quotes.ui.add_quote.AddQuoteActivity

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val onSaveQuoteRegisterForActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            activityResult.data?.let {
                if (it.hasExtra(TAG_SAVE_QUOTE)) {
                    val quote = when {
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                            it.getParcelableExtra(TAG_SAVE_QUOTE, Quote::class.java)
                        }
                        else -> {
                            it.getParcelableExtra<Quote>(TAG_SAVE_QUOTE)
                        }
                    }

                    viewModel.save(quote!!)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeData()
        setListeners()
        setupObservers()
    }

    private fun initializeData() = viewModel.initialize()

    private fun setListeners() = setAddListeners()

    private fun setupObservers() = setQuoteListObserver()

    private fun setQuoteListObserver() {
        viewModel.quoteList.observe(this) {
            binding.apply {
                if (it.isEmpty()) {
                    recycleViewQuotes.visibility = View.GONE
                    textEmptyQuotes.visibility = View.VISIBLE
                } else {
                    textEmptyQuotes.visibility = View.GONE
                    recycleViewQuotes.apply {
                        visibility = View.VISIBLE
                        adapter = QuoteAdapter(quoteList = it)
                    }
                }
            }

        }
    }

    private fun setAddListeners() {
        binding.addNewQuote.setOnClickListener { onPressAdd() }
        binding.addNewQuote.setOnLongClickListener {
            onLongPressAdd()
            it.isLongClickable
        }
    }

    private fun onPressAdd() = Intent(this, AddQuoteActivity::class.java).let {
        onSaveQuoteRegisterForActivity.launch(it)
    }

    private fun onLongPressAdd() {
        viewModel.clear()
        Toast.makeText(this, R.string.list_clear, Toast.LENGTH_LONG).show()
    }


    companion object {
        const val TAG_SAVE_QUOTE = "SAVE_QUOTE"
    }
}