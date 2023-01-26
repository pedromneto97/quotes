package br.eng.pedro_mendes.quotes.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.eng.pedro_mendes.quotes.data.Quote
import br.eng.pedro_mendes.quotes.databinding.QuoteListItemBinding

class QuoteAdapter(
    private val quoteList: List<Quote>
) : Adapter<QuoteAdapter.ViewHolder>() {
    private lateinit var binding: QuoteListItemBinding

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Quote) {
            binding.apply {
                textAuthorName.text = item.author
                textQuote.text = item.quote
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = QuoteListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = quoteList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = quoteList[position])
    }
}