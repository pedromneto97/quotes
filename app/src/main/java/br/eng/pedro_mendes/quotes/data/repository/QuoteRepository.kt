package br.eng.pedro_mendes.quotes.data.repository

import br.eng.pedro_mendes.quotes.data.Quote

class QuoteRepository(newList: MutableList<Quote>) {
    private val quoteList: MutableList<Quote> = newList

    fun save(quote: Quote) = quoteList.add(quote)


    fun clear() = quoteList.clear()

    fun get(): List<Quote> = quoteList.toList()
}