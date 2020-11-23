package it.fabiosassu.pokedex.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import it.fabiosassu.pokedex.R
import it.fabiosassu.pokedex.model.Pokemon
import it.fabiosassu.pokedex.util.GlideApp
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonAdapter(private val onClickCallback: (pkmnId: Int?) -> Unit) :
    PagingDataAdapter<Pokemon, ItemViewHolder>(POKEMON_COMPARATOR) {

    companion object {
        private val POKEMON_COMPARATOR = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
                        && oldItem.number == newItem.number
                        && oldItem.url == newItem.url
            }
        }
        const val LOADING_VIEW_TYPE = 0
        const val POKEMON_TYPE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            LOADING_VIEW_TYPE
        } else {
            POKEMON_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)
        return ItemViewHolder(view) { id: Int? -> onClickCallback.invoke(id) }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it) }
    }

}

class ItemViewHolder(itemView: View, private val onClickCallback: (pkmnId: Int?) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.pkmn_image
    private val titleText: TextView = itemView.pkmn_name

    fun bindItem(pokemon: Pokemon) {
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        GlideApp.with(itemView.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.number}.png")
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .placeholder(R.drawable.ic_placeholder)
            .into(imageView)
        titleText.text = pokemon.name

        itemView.setOnClickListener {
            onClickCallback.invoke(pokemon.number)
        }
    }
}