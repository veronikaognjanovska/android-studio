package com.example.lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab3.adapters.MovieAdapter
import com.example.lab3.api.OmdbApi
import com.example.lab3.api.OmdbApiClient
import com.example.lab3.databinding.FragmentFirstBinding
import com.example.lab3.models.Movie
import com.example.lab3.viewmodel.FirstFragmentViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var omdbApi: OmdbApi
    private lateinit var searchMovieTitle: EditText
    private lateinit var currentMovieTitle: TextView
    private lateinit var currentMovieYear: TextView
    private lateinit var currentMovieImage: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: MovieAdapter
    private lateinit var firstFragmentViewModel: FirstFragmentViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.currentMovieTitle = view.findViewById<TextView>(R.id.currentMovieTitle)
        this.currentMovieYear = view.findViewById<TextView>(R.id.currentMovieYear)
        this.currentMovieImage = view.findViewById<ImageView>(R.id.currentMovieImage)
        this.searchMovieTitle = view.findViewById<EditText>(R.id.searchMovieTitle)
        this.searchMovieTitle.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                val title: String = this.searchMovieTitle.text.toString()
                this.firstFragmentViewModel.searchMovieByTitle(title)
                true
            } else {
                Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                false
            }
        }



        this.omdbApi = OmdbApiClient.getOmdbApi()!!

        this.firstFragmentViewModel =
            ViewModelProvider(this).get(FirstFragmentViewModel::class.java)
        this.firstFragmentViewModel.getMovieMutableLiveData()
            .observe(viewLifecycleOwner, object : Observer<Movie?> {
                override fun onChanged(t: Movie?) {
                    if (t != null) {
                        displayData(t)
                    } else {
                        Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                    }
                }
            })
        this.firstFragmentViewModel.getMoviesListMutableLiveData()
            .observe(viewLifecycleOwner, object : Observer<MutableList<Movie>?> {
                override fun onChanged(t: MutableList<Movie>?) {
                    if (t != null) {
                        recyclerViewAdapter.updateData(t)
                    } else {
                        Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                    }
                }
            })

        this.recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        this.recyclerView.layoutManager = LinearLayoutManager(activity)
        var p = this.firstFragmentViewModel.getMoviesListMutableLiveData().value
        this.recyclerViewAdapter =
            MovieAdapter(
                requireActivity().applicationContext,
                this.firstFragmentViewModel.getMoviesListMutableLiveData().value!!
            )
        this.recyclerView.adapter = this.recyclerViewAdapter
        this.recyclerViewAdapter.onItemClick = { movie ->
            val bundle: Bundle = Bundle()
            bundle.putString("imdbID", movie.imdbID) // Serializable Object
            findNavController().navigate(
                R.id.action_FirstFragment_to_SecondFragment,
                bundle
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayData(data: Movie) {
        currentMovieTitle.text = data.Title
        currentMovieYear.text = data.Year.toString()
        Glide.with(this).load(data.Poster).into(currentMovieImage)
    }

}