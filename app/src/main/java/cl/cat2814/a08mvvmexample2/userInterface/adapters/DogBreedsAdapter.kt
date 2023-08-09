package cl.cat2814.a08mvvmexample2.userInterface.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import cl.cat2814.a08mvvmexample2.R
import cl.cat2814.a08mvvmexample2.data.local.DogBreedsEntity
import cl.cat2814.a08mvvmexample2.databinding.ItemDogBreedBinding

class DogBreedsAdapter : RecyclerView.Adapter<DogBreedsAdapter.ItemDogBreedViewHolder>() {

    private lateinit var binding: ItemDogBreedBinding
    private val dogBreedsList = mutableListOf<DogBreedsEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogBreedsAdapter.ItemDogBreedViewHolder {
        binding = ItemDogBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemDogBreedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogBreedsAdapter.ItemDogBreedViewHolder, position: Int) {
        val dogBreeds = dogBreedsList[position]
        holder.bind(dogBreeds)

        holder.binding.cvDogBreed.startAnimation(
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade_slide_left)
        )
    }

    override fun getItemCount(): Int {
        return dogBreedsList.size
    }

    fun setData(dogBreeds: List<DogBreedsEntity>) {
        this.dogBreedsList.clear()
        this.dogBreedsList.addAll(dogBreeds)
        notifyDataSetChanged()
    }

    class ItemDogBreedViewHolder(val binding: ItemDogBreedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dogBreeds: DogBreedsEntity) {
            binding.tvDogBreedName.text = dogBreeds.breed
        }
    }
}
