package br.eng.pedro_mendes.quotes.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.eng.pedro_mendes.quotes.data.Quote
import br.eng.pedro_mendes.quotes.data.repository.QuoteRepository

class MainViewModel : ViewModel() {
    private val quoteRepository: QuoteRepository = QuoteRepository(mutableListOf())
    private val innerQuoteList = MutableLiveData<List<Quote>>()

    val quoteList: LiveData<List<Quote>> = innerQuoteList

    fun initialize() {
        updateQuoteList()
    }

    fun save(quote: Quote) {
        Log.i("IGTI_SAVE", quote.toString())
        quoteRepository.save(quote)

        updateQuoteList()
    }

    fun clear() {
        quoteRepository.clear()
        updateQuoteList()
    }

    private fun updateQuoteList() {
        innerQuoteList.value = quoteRepository.get()
    }

}