package cl.cat2814.a08mvvmexample2.userInterface.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cl.cat2814.a08mvvmexample2.databinding.FragmentDogBreedsListBinding
import cl.cat2814.a08mvvmexample2.userInterface.adapters.DogBreedsAdapter
import cl.cat2814.a08mvvmexample2.userInterface.viewModel.DogBreedsViewModel

class DogBreedsListFragment : Fragment() {

    lateinit var binding: FragmentDogBreedsListBinding
    private val dogBreedsViewModel: DogBreedsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogBreedsListBinding.inflate(layoutInflater, container, false)

        initAdapter()

        dogBreedsViewModel.getAllDogBreeds()

        return binding.root
    }

    private fun initAdapter() {
        val adapter = DogBreedsAdapter()
        binding.rvDogBreedsList.adapter = adapter
        dogBreedsViewModel.liveDataDogBreeds().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }
}
