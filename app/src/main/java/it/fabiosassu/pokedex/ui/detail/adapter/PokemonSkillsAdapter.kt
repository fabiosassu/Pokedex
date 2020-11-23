package it.fabiosassu.pokedex.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import it.fabiosassu.pokedex.R
import kotlinx.android.synthetic.main.layout_skill_item.view.*

/**
 * Simple adapter that is used in the [PokemonDetailFragment] to render the skills list
 *
 * @property items MutableList<Pair<String, String>> the list of items we want to be set
 * @constructor
 */
class PokemonSkillsAdapter(
    private val items: MutableList<Pair<String, Int>>?
) :
    RecyclerView.Adapter<PokemonSkillsAdapter.ViewHolder>() {

    class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(skill: Pair<String, Int>?) {
            itemView.skill_type.text = skill?.first
            itemView.skill_value.text = skill?.second?.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_skill_item,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items?.get(position))
    }


}