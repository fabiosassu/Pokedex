package it.fabiosassu.pokedex.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import it.fabiosassu.pokedex.R
import kotlinx.android.synthetic.main.pokemon_loading_item.view.*

class PostsLoadStateAdapter(
    private val adapter: PokemonAdapter
) : LoadStateAdapter<NetworkStateItemViewHolder>() {

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_loading_item, parent, false)
        return NetworkStateItemViewHolder(view) { adapter.retry() }
    }
}

class NetworkStateItemViewHolder(
    itemView: View,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val progressBar = itemView.progress_bar
    private val errorMsg = itemView.error_message
    private val retry = itemView.retry_button
        .also {
            it.setOnClickListener { retryCallback() }
        }

    fun bindTo(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        retry.isVisible = loadState is LoadState.Error
        errorMsg.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
        errorMsg.text = (loadState as? LoadState.Error)?.error?.message
    }

}