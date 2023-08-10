package cl.cat2814.a08mvvmexample2.userInterface.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import cl.cat2814.a08mvvmexample2.R
import cl.cat2814.a08mvvmexample2.data.local.DogBreedsImagesEntity
import cl.cat2814.a08mvvmexample2.databinding.ItemDogBreedImageBinding
import coil.load
import coil.transform.RoundedCornersTransformation

class DogBreedImagesAdapter: RecyclerView.Adapter<DogBreedImagesAdapter.ItemDogBreedImagesViewHolder>() {

    lateinit var binding: ItemDogBreedImageBinding
    private val listDogBreedsImages = mutableListOf<DogBreedsImagesEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogBreedImagesAdapter.ItemDogBreedImagesViewHolder {
        binding = ItemDogBreedImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemDogBreedImagesViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DogBreedImagesAdapter.ItemDogBreedImagesViewHolder,
        position: Int
    ) {
        val dogBreed = listDogBreedsImages[position]
        holder.bind(dogBreed)

        holder.binding.cvDogBreedImage.startAnimation(
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade_slide_right)
        )
    }

    fun setImageData(dogBreedImages : List<DogBreedsImagesEntity>){
        this.listDogBreedsImages.clear()
        this.listDogBreedsImages.addAll(dogBreedImages)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listDogBreedsImages.size
    }

    class ItemDogBreedImagesViewHolder(val binding: ItemDogBreedImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dogBreed: DogBreedsImagesEntity) {
            binding.ivDogBreedImage.load(dogBreed.imgUrl) {
                transformations(RoundedCornersTransformation(20f))
            }
        }
    }
}
