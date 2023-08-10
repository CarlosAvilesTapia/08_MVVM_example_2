package cl.cat2814.a08mvvmexample2.userInterface.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cl.cat2814.a08mvvmexample2.databinding.FragmentDogBreedImagesBinding
import cl.cat2814.a08mvvmexample2.userInterface.viewModel.DogBreedsViewModel

private const val ARG_PARAM1 = "id"

class DogBreedImagesFragment : Fragment() {

    private var param1: String? = null
    lateinit var binding: FragmentDogBreedImagesBinding
    private val dogBreedsViewModel: DogBreedsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogBreedImagesBinding.inflate(layoutInflater, container, false)

        initAdapter()

        dogBreedsViewModel.getDogBreedImages(param1.toString())



        return binding.root
    }

    private fun initAdapter() {
        TODO("Not yet implemented")
    }
}
