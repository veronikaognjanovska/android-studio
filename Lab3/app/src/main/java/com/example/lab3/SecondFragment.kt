package com.example.lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.lab3.databinding.FragmentSecondBinding
import com.example.lab3.models.Movie
import com.example.lab3.viewmodel.SecondFragmentViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var secondFragmentViewModel: SecondFragmentViewModel
    private lateinit var movieDetailsImagePoster: ImageView
    private lateinit var movieDetailsTitle: TextView
    private lateinit var movieDetailsID: TextView
    private lateinit var movieDetailsReleased: TextView
    private lateinit var movieDetailsGenre: TextView
    private lateinit var movieDetailsRuntime: TextView
    private lateinit var movieDetailsCountry: TextView
    private lateinit var movieDetailsLanguage: TextView
    private lateinit var movieDetailsDirectior: TextView
    private lateinit var movieDetailsActors: TextView
    private lateinit var movieDetailsPlot: TextView


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.setMovieDetails(view)

        val imdbID: String = arguments?.getSerializable("imdbID") as String
        secondFragmentViewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)
        secondFragmentViewModel.loadDataFromDatabase(imdbID)
        secondFragmentViewModel.getMovieMutableLiveData()
            .observe(viewLifecycleOwner, object : Observer<Movie?> {
                override fun onChanged(t: Movie?) {
                    if (t != null) {
                        displayDataFromDatabase(t)
                    } else {
                        Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                    }
                }
            })

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setMovieDetails(view: View) {
        movieDetailsImagePoster = view.findViewById(R.id.movieDetailsImagePoster)
        movieDetailsTitle = view.findViewById(R.id.movieDetailsTitle)
        movieDetailsID = view.findViewById(R.id.movieDetailsID)
        movieDetailsReleased = view.findViewById(R.id.movieDetailsReleased)
        movieDetailsGenre = view.findViewById(R.id.movieDetailsGenre)
        movieDetailsRuntime = view.findViewById(R.id.movieDetailsRuntime)
        movieDetailsCountry = view.findViewById(R.id.movieDetailsCountry)
        movieDetailsLanguage = view.findViewById(R.id.movieDetailsLanguage)
        movieDetailsDirectior = view.findViewById(R.id.movieDetailsDirectior)
        movieDetailsActors = view.findViewById(R.id.movieDetailsActors)
        movieDetailsPlot = view.findViewById(R.id.movieDetailsPlot)
    }

    fun displayDataFromDatabase(movie: Movie) {
        Glide.with(this).load(movie.Poster).into(movieDetailsImagePoster)
        movieDetailsTitle.text = movie.Title
        movieDetailsID.text = movie.imdbID
        movieDetailsReleased.text = movie.Released
        movieDetailsGenre.text = movie.Genre
        movieDetailsRuntime.text = movie.Runtime
        movieDetailsCountry.text = movie.Country
        movieDetailsLanguage.text = movie.Language
        movieDetailsDirectior.text = movie.Director
        movieDetailsActors.text = movie.Actors
        movieDetailsPlot.text = movie.Plot
    }

}