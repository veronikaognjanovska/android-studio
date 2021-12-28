package com.example.retrofitapp

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapp.adapters.TrackAdapter
import com.example.retrofitapp.database.relationship.PlaylistWithTracks
import com.example.retrofitapp.databinding.FragmentSecondBinding
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.viewmodel.SecondFragmentViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var secondFragmentViewModel: SecondFragmentViewModel
    private lateinit var trackRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: TrackAdapter
    private lateinit var plTitle: TextView
    private lateinit var plImage: ImageView

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

        this.plTitle = view.findViewById(R.id.plId)
        this.plImage = view.findViewById(R.id.plImage)
        this.trackRecyclerView = view.findViewById(R.id.databasePlaylist)
        this.trackRecyclerView.layoutManager = LinearLayoutManager(activity)
        this.recyclerViewAdapter =
            TrackAdapter(requireActivity().applicationContext, mutableListOf<Data>())
        this.trackRecyclerView.setHasFixedSize(true)
        this.trackRecyclerView.adapter = this.recyclerViewAdapter

        secondFragmentViewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)
        secondFragmentViewModel.loadDataFromDatabase(3773404202)
        secondFragmentViewModel.getPlaylistWithTracksMutableLiveData()
            .observe(viewLifecycleOwner, object : Observer<PlaylistWithTracks?> {
                override fun onChanged(t: PlaylistWithTracks?) {
                    if (t != null) {
                        // show data in recycler view
                        displayDataFromDatabase(t)
                    } else {
                        Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                    }
                }
            })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun displayDataFromDatabase(data: PlaylistWithTracks) {
        plTitle.text = data.playlist.title
        Glide.with(this).load(data.playlist.picture).into(plImage)
        this.recyclerViewAdapter.updateData(data.tracks)
    }
}