package com.example.lab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.adapters.ActorAdapter
import com.example.lab2.databinding.FragmentDetailsBinding
import com.example.lab2.models.Movie

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private lateinit var movieId: TextView
    private lateinit var movieTitle: TextView
    private lateinit var movieDescription: TextView
    private lateinit var directorId: TextView
    private lateinit var directorName: TextView
    private lateinit var directorSurname: TextView
    private lateinit var directorYear: TextView
    private lateinit var recyclerViewActors: RecyclerView
    private lateinit var recyclerViewAdapter: ActorAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.setMovieDetails(view)

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_FragmentMovieDetails_to_FragmentMovieList)
        }
    }

    private fun setMovieDetails(view: View) {
        var movie: Movie = arguments?.getSerializable("MOVIE") as Movie
        movieId = view.findViewById(R.id.movieId)
        movieTitle = view.findViewById(R.id.movieTitle)
        movieDescription = view.findViewById(R.id.movieDescription)
        directorId = view.findViewById(R.id.directorId)
        directorName = view.findViewById(R.id.directorName)
        directorSurname = view.findViewById(R.id.directorSurname)
        directorYear = view.findViewById(R.id.directorYear)
        recyclerViewActors = view.findViewById(R.id.recyclerViewActors)

        movieId.text = getString(R.string.id_label, movie.id.toString())
        movieTitle.text = movie.title
        movieDescription.text = movie.description
        directorId.text = getString(R.string.id_label, movie.director.id.toString())
        directorName.text = movie.director.name
        directorSurname.text = movie.director.surname
        directorYear.text = movie.director.birthYear.toString()

        this.recyclerViewActors.layoutManager = LinearLayoutManager(activity)
        this.recyclerViewAdapter =
            ActorAdapter(requireActivity().applicationContext, movie.actors)
        this.recyclerViewActors.adapter = this.recyclerViewAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}