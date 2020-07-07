package com.example.mxplayerdemo

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mxplayerdemo.databinding.ActivityViewAllVideosBinding
import com.example.mxplayerdemo.databinding.CustomVideoViewBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.material.internal.ContextUtils
import com.google.firebase.database.*
import com.google.firebase.database.annotations.NotNull
import com.google.firebase.storage.FirebaseStorage

private const val VIDEO_URI = "video_url"
private  var globalLikeCount = 0
private var isLiked = false

class ViewAllVideos : AppCompatActivity() {

    private lateinit var binding: ActivityViewAllVideosBinding

    private lateinit var fireRef: DatabaseReference

    private lateinit var adapter: CustomExoRecyclerAdapter

    private lateinit var option: FirebaseRecyclerOptions<UsersVideoData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_all_videos)

        fireRef = FirebaseDatabase.getInstance().getReference("UsersDatabase")

        fireRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("AXE", "Error 2 fetch: $error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {


                    binding.idProgressBar.visibility = View.GONE
                    binding.idLottieView.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE

                } else {

                    binding.idProgressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.idLottieView.visibility = View.VISIBLE
                }
            }
        })

        option = FirebaseRecyclerOptions.Builder<UsersVideoData>()
            .setQuery(fireRef,UsersVideoData::class.java)
            .build()

        adapter = CustomExoRecyclerAdapter(this, option)
        binding.recyclerView.adapter = adapter
    }



    @SuppressLint("RestrictedApi")
    private fun firebaseSearch(searchText : String?) {

        val query = fireRef.orderByChild("videoname")
            .startAt(searchText).endAt(searchText + "\uf8ff")

        val mOption = FirebaseRecyclerOptions.Builder<UsersVideoData>()
            .setQuery(query, UsersVideoData::class.java)
            .setLifecycleOwner(this)
            .build()

        adapter = CustomExoRecyclerAdapter(this,mOption)
        binding.recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search,menu)
        val searchItem = menu?.findItem(R.id.search)

        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(inputText: String?): Boolean {
                firebaseSearch(inputText)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                firebaseSearch(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}

class CustomExoRecyclerAdapter(private var context: Context, @NotNull option : FirebaseRecyclerOptions<UsersVideoData>)
    : FirebaseRecyclerAdapter<UsersVideoData, CustomExoRecyclerAdapter.ViewHolder>(option){

    class ViewHolder(var binding : CustomVideoViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(CustomVideoViewBinding.inflate(
        LayoutInflater.from(parent.context),parent,false))

    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int, usersData: UsersVideoData) {
        holder.binding.idRawVideoName.text = usersData.videoname
        holder.binding.idVideoSize.text = "${usersData.videosize}MB"
        holder.binding.idLikeCount.text = usersData.likeCount.toString()

        val simplePlayer = ExoPlayerFactory.newSimpleInstance(context)
        simplePlayer.prepare(buildMediaSource(usersData.videourl.toUri()))
        holder.binding.idPlayerView.player = simplePlayer

        holder.binding.idPlayerView.setOnClickListener {
            val intent = Intent(context,SingleVideo::class.java)
            intent.putExtra(VIDEO_URI,usersData.videourl)

            val data = arrayOf(android.util.Pair(holder.binding.idPlayerView as View,"mVideo"))
            val actOption = ActivityOptions.makeSceneTransitionAnimation (ContextUtils.getActivity(context), *data)
            ContextCompat.startActivity(context,intent,actOption.toBundle())
        }
        // onclick delete icon delete image & its meta data
        holder.binding.isDeleteIcon.setOnClickListener {

            // delete database & then storage
            getRef(position).removeValue()
                .addOnSuccessListener {
                    Toast.makeText(context, "DB data deleted", Toast.LENGTH_SHORT).show()
                }

            FirebaseStorage.getInstance().getReference("UsersStorage/${usersData.videoname}")
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "\n\n\nVideo deleted from Storage", Toast.LENGTH_SHORT).show()
                }
        }

        holder.binding.heartButton.setOnClickListener {



            getRef(position).runTransaction(object : Transaction.Handler{
                override fun onComplete(error: DatabaseError?, committed: Boolean, currentData: DataSnapshot?) {
                    Log.e("AXE","Transaction Error  : $error")
                }

                override fun doTransaction(currentData: MutableData): Transaction.Result {
                    val currentUser = currentData.getValue(UsersVideoData::class.java) ?: return Transaction.success(currentData)
                    Log.e("AXE","Like count : ${currentUser.likeCount}")
                    if(isLiked)
                    {
                        ++currentUser.likeCount
                        holder.binding.heartButton.setImageResource(R.drawable.ic_unlike)
                        isLiked = false
                        Log.e("ASP","Like it")
                    }else{
                        --currentUser.likeCount
                        holder.binding.heartButton.setImageResource(R.drawable.ic_love_fill)
                        isLiked = true
                        Log.e("ASP","UN-Like it")

                    }

                    currentData.value = currentUser

                    return Transaction.success(currentData)

                }
            })
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource? {
        return ProgressiveMediaSource.Factory(DefaultDataSourceFactory(context, "Exo_Player_Demo")).createMediaSource(uri)
    }
}

