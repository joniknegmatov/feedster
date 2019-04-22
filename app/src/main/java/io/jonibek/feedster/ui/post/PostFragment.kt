package io.jonibek.feedster.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.jonibek.feedster.R
import io.jonibek.feedster.databinding.FragmentPostBinding
import javax.inject.Inject

class PostFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: PostFragmentViewModel.Factory

    private lateinit var viewModel: PostFragmentViewModel
    private lateinit var binding: FragmentPostBinding
    private lateinit var commentAdapter: CommentAdapter
    private var postId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            postId = getInt(POST_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupRecyclerView()
        setupViewModel()
        loadDataIfNeeded(savedInstanceState == null)
        subscribeToData()
    }

    private fun setupRecyclerView() {
        commentAdapter = CommentAdapter()
        binding.rvComments.layoutManager = LinearLayoutManager(context)
        binding.rvComments.adapter = commentAdapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostFragmentViewModel::class.java)
        viewModel.postId = postId
        binding.vm = viewModel
    }

    private fun loadDataIfNeeded(loadNeeded: Boolean) {
        if (loadNeeded) {
            postId?.let {
                viewModel.loadData()
            }
        }
    }

    private fun subscribeToData() {
        viewModel.commentListLiveData.observe(this, Observer { list ->
            list?.let {
                commentAdapter.renderData(it)
            }
        })
    }


    companion object {
        const val POST_ID = "POST_ID"
    }

}