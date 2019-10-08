package io.jonibek.feedster.presentation.feed

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment
import io.jonibek.feedster.R
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.databinding.FragmentFeedBinding
import io.jonibek.feedster.di.viewmodel.DaggerViewModelFactory
import io.jonibek.feedster.domain.feeditem.FeedItemUseCase
import io.jonibek.feedster.presentation.feed.feeditem.FeedsAdapter
import io.jonibek.feedster.presentation.post.PostFragment.Companion.POST_ID
import javax.inject.Inject


class FeedsFragment : DaggerFragment(), FeedsAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    @Inject
    lateinit var feedItemUseCase: FeedItemUseCase

    private lateinit var binding: FragmentFeedBinding
    private lateinit var fragmentViewModel: FeedsFragmentViewModel
    private lateinit var feedsAdapter: FeedsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()
        loadDataIfNeeded(savedInstanceState == null)
        subscribeToData()
    }

    private fun setupRecyclerView() {
        feedsAdapter = FeedsAdapter(this, feedItemUseCase)
        binding.rvPosts.layoutManager = LinearLayoutManager(context)
        binding.rvPosts.adapter = feedsAdapter
    }

    private fun setupViewModel() {
        fragmentViewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(FeedsFragmentViewModel::class.java)
        binding.vm = fragmentViewModel
    }

    private fun loadDataIfNeeded(loadNeeded: Boolean) {
        if (loadNeeded) {
            fragmentViewModel.loadPosts()
        }
    }

    private fun subscribeToData() {
        fragmentViewModel.postsLiveData.observe(this, Observer { posts ->
            posts?.let {
                feedsAdapter.renderData(it)
            }
        })
    }

    override fun onItemClick(post: Post) {
        Navigation.findNavController(binding.root).navigate(
            R.id.action_feeds_screen_to_full_post, Bundle().apply {
                putInt(POST_ID, post.id!!)
            })
    }
}