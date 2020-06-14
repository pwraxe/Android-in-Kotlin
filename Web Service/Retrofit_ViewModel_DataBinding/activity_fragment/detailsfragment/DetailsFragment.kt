package com.example.retrofit_viewmodel_databinding.detailsfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofit_viewmodel_databinding.R
import com.example.retrofit_viewmodel_databinding.databinding.FragmentDetailsBinding
import com.example.retrofit_viewmodel_databinding.retrofit.UserData

class DetailsFragment : Fragment() {

    private var binding : FragmentDetailsBinding? = null
    private var detailViewModel : DetailViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details,container,false)

        val user_Data = DetailsFragmentArgs.fromBundle(requireArguments())
        val usrObj  = UserData(user_Data.clickedPic,user_Data.viewCount,user_Data.downloadCount,
        user_Data.favoritesCount,user_Data.likeCount,user_Data.commentCount,user_Data.username,user_Data.profilePic)

        val viewModelFactory = DetailViewModelFactory(usrObj)
        detailViewModel = ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)

        // it binds xml file with viewModel  , due to this no need write observer code
        binding?.detailVM = detailViewModel
        binding?.lifecycleOwner = this



//        detailViewModel?.userData?.observe(viewLifecycleOwner, Observer { data ->
//
//            binding?.idDetailUserImage?.let {
//                Glide.with(requireContext()).load(data.userImageURL)
//                    .apply(RequestOptions()
//                        .placeholder(R.drawable.loading_animation)
//                        .error(R.drawable.ic_broken_image))
//                    .into(it)
//            }
//
//            binding?.idDetailUsername?.text = data.user
//            binding?.idDetailViewCount?.text = data.views.toString()
//
//            binding?.idDetailImageView?.let {
//                Glide.with(requireContext()).load(data.largeImageURL)
//                    .apply(RequestOptions()
//                        .placeholder(R.drawable.loading_animation)
//                        .error(R.drawable.ic_broken_image))
//                    .into(it)
//            }
//
//            binding?.idDetailLikeCount?.text = data.likes.toString()
//            binding?.idDetailCommentCount?.text = data.comments.toString()
//            binding?.idDetailFavoritesCount?.text = data.favorites.toString()
//            binding?.idDetailDownloadCount?.text = data.downloads.toString()
//        })


        return binding?.root

    }

}