package com.example.retrofitapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapp.adapters.TrackAdapter
import com.example.retrofitapp.api.DeezerApi
import com.example.retrofitapp.api.DeezerApiClient
import com.example.retrofitapp.databinding.FragmentFirstBinding
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.models.Playlist
import com.example.retrofitapp.viewmodel.FirstFragmentViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var deezerApiClient: DeezerApi
    private lateinit var textViewPlaylistTitle: TextView
    private lateinit var imageViewPlaylistPicture: ImageView
    private lateinit var trackRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: TrackAdapter

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

        this.firstFragmentViewModel =
            ViewModelProvider(this).get(FirstFragmentViewModel::class.java)
        this.firstFragmentViewModel.getPlaylistMutableLiveData()
            .observe(viewLifecycleOwner, object : Observer<Playlist?> {
                override fun onChanged(t: Playlist?) {
                    if (t != null) {
                        displayData(t)
                    } else {
                        Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                    }
                }
            })

        // !! - there will be a implementation in any case
        this.deezerApiClient = DeezerApiClient.getDeezerApi()!!

        var playlistId: EditText = view.findViewById<EditText>(R.id.editTextPlaylistID)
        this.textViewPlaylistTitle = view.findViewById<TextView>(R.id.textViewPLaylistTitle)
        this.imageViewPlaylistPicture = view.findViewById<ImageView>(R.id.imageViewPlaylistPicture)

        this.trackRecyclerView = view.findViewById(R.id.allTracksRecyclerView)
        this.trackRecyclerView.layoutManager = LinearLayoutManager(activity)
        this.recyclerViewAdapter =
            TrackAdapter(requireActivity().applicationContext, mutableListOf<Data>())
        this.trackRecyclerView.adapter = this.recyclerViewAdapter

        playlistId.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                val listId: String = playlistId.text.toString()
                this.firstFragmentViewModel.searchPlaylistById(listId)
                true
            } else {
                Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                false
            }
        }

        var secondFragmentButton: Button = view.findViewById<Button>(R.id.secondFragmentActivity)
        secondFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

//    private fun searchPlaylistById(id: String) {
//        this.deezerApiClient.getPlaylistById(id).enqueue(object : Callback<Playlist> {
//            override fun onResponse(call: Call<Playlist>?, response: Response<Playlist>) {
//                displayData(response.body())
//                if (response.code() == 200) {
//                    Toast.makeText(activity, "Success!", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Playlist>?, t: Throwable?) {
//                Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }

    private fun displayData(data: Playlist) {
        textViewPlaylistTitle.text = data.title
        Glide.with(this).load(data.picture).into(imageViewPlaylistPicture)
        this.recyclerViewAdapter.updateData(data.tracks.data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //3773404202
//    1578812305
}