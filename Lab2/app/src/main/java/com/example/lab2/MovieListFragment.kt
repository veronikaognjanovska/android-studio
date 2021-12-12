package com.example.lab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.adapters.MovieAdapter
import com.example.lab2.api.MovieFakeApi
import com.example.lab2.databinding.FragmentMovieListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private lateinit var recyclerViewMovie: RecyclerView
    private lateinit var recyclerViewAdapter: MovieAdapter
    private lateinit var movieFakeApi: MovieFakeApi

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.movieFakeApi = MovieFakeApi.getInstance()

        this.recyclerViewMovie = view.findViewById(R.id.recyclerViewMovie)
        this.recyclerViewMovie.layoutManager = LinearLayoutManager(activity)
        this.recyclerViewAdapter =
            MovieAdapter(requireActivity().applicationContext, this.movieFakeApi.getMovies())
        this.recyclerViewMovie.adapter = this.recyclerViewAdapter
        this.recyclerViewAdapter.onItemClick = { movie ->
            var movie2 = movie
            val bundle: Bundle = Bundle()
            bundle.putSerializable("MOVIE", movie) // Serializable Object
            findNavController().navigate(
                R.id.action_FragmentMovieList_to_FragmentMovieDetails,
                bundle
            )
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}