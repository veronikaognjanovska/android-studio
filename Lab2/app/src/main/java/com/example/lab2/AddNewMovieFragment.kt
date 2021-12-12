package com.example.lab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.adapters.ActorAdapter
import com.example.lab2.api.MovieFakeApi
import com.example.lab2.databinding.FragmentAddNewMovieBinding
import com.example.lab2.dialog.AddNewActorDialog
import com.example.lab2.models.Actor
import com.example.lab2.models.Director
import com.example.lab2.models.Movie

class AddNewMovieFragment : Fragment(), AddNewActorDialog.AddNewActorDialogListener {

    private var _binding: FragmentAddNewMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var addNewMovieTitle: EditText
    private lateinit var addNewMovieDescription: EditText
    private lateinit var addNewDirectorName: EditText
    private lateinit var addNewDirectorSurname: EditText
    private lateinit var addNewDirectorYear: EditText
    private var actorsList: MutableList<Actor> = mutableListOf()
    private lateinit var recyclerViewActor: RecyclerView
    private lateinit var recyclerViewAdapter: ActorAdapter
    private lateinit var movieFakeApi: MovieFakeApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity?)!!.hideFloatingActionButton()
        _binding = FragmentAddNewMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.movieFakeApi = MovieFakeApi.getInstance()
        addNewMovieTitle = view.findViewById(R.id.addNewMovieTitle)
        addNewMovieDescription = view.findViewById(R.id.addNewMovieDescription)
        addNewDirectorName = view.findViewById(R.id.addNewDirectorName)
        addNewDirectorSurname = view.findViewById(R.id.addNewDirectorSurname)
        addNewDirectorYear = view.findViewById(R.id.addNewDirectorYear)

        this.recyclerViewActor = view.findViewById(R.id.recyclerViewAddNewActors)
        this.recyclerViewActor.layoutManager = LinearLayoutManager(activity)
        this.recyclerViewAdapter =
            ActorAdapter(requireActivity().applicationContext, mutableListOf())
        this.recyclerViewActor.adapter = this.recyclerViewAdapter

        binding.addNewActorButton.setOnClickListener {
            this.openDialog()
        }

        binding.addNewSaveButton.setOnClickListener {
            var saved: Boolean = this.saveNewMovie()
            if (saved) {
                (activity as MainActivity?)!!.showFloatingActionButton()
                findNavController().navigate(R.id.action_addNewMovieFragment_to_FragmentMovieList)
            }
        }

        binding.addNewBackButton.setOnClickListener {
            (activity as MainActivity?)!!.showFloatingActionButton()
            findNavController().navigate(R.id.action_addNewMovieFragment_to_FragmentMovieList)
        }

    }

    private fun openDialog() {
        var dialogInstance = AddNewActorDialog()
        dialogInstance.setAddNewActorDialogListener(this)
        fragmentManager?.let { dialogInstance.show(it, "") }
    }

    override fun setText(actorName: String, actorSurname: String, actorYear: Int) {
        var actor: Actor = Actor(randomID(), actorName, actorSurname, actorYear)
        this.actorsList.add(actor)
        this.recyclerViewAdapter.updateData(this.actorsList)
        Toast.makeText(activity, "Actor added!", Toast.LENGTH_LONG).show()
    }

    private fun randomID(): Long = List(8) {
        (('0'..'9')).random()
    }.joinToString("").toLong()

    private fun saveNewMovie(): Boolean {
        var directorName = addNewDirectorName.text.toString()
        var directorSurname = addNewDirectorSurname.text.toString()
        var directorYear = addNewDirectorYear.text.toString()
        var movieTitle = addNewMovieTitle.text.toString()
        var movieDescription = addNewMovieDescription.text.toString()
        if (directorName == "" || directorSurname == "" || directorYear == "" || movieTitle == ""
            || movieDescription == "" || directorYear.toIntOrNull() == null
        ) {
            Toast.makeText(activity, "Enter the correct data!", Toast.LENGTH_LONG).show()
            return false
        }
        var director: Director =
            Director(randomID(), directorName, directorSurname, directorYear.toInt())
        var movie: Movie = Movie(randomID(), movieTitle, movieDescription, director, actorsList)
        this.movieFakeApi.addNewMovie(movie)
        Toast.makeText(activity, "Movie added!", Toast.LENGTH_LONG).show()
        return true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}