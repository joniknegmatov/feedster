package io.jonibek.feedster.presentation.post

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import io.jonibek.feedster.data.entities.Comment
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.data.entities.User
import io.jonibek.feedster.domain.post.PostUseCase
import io.jonibek.feedster.presentation.internal.ObservableViewModel
import io.jonibek.feedster.util.RxSchedulers
import io.jonibek.feedster.util.addTo
import io.jonibek.feedster.util.withNetworkSchedulers
import javax.inject.Inject


class PostFragmentViewModel
@Inject
constructor(
    private val postUseCase: PostUseCase,
    private val rxSchedulers: RxSchedulers
) : ObservableViewModel() {

    var postId: Int? = null

    val commentListLiveData: MutableLiveData<List<Comment>> by lazy { MutableLiveData<List<Comment>>() }

    @get:Bindable
    var loadingInProgress: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loadingInProgress)
        }

    @get:Bindable
    var showReloadButton: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showReloadButton)
        }

    @get:Bindable
    var postTitle: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.postTitle)
        }

    @get:Bindable
    var postBody: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.postBody)
        }

    @get:Bindable
    var postAuthor: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.postAuthor)
        }

    @get:Bindable
    var commentsCount: String = ""
        set(value) {
            field = "($value)"
            notifyPropertyChanged(BR.commentsCount)
        }

    @get:Bindable
    var commentSectionVisibility: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.commentSectionVisibility)
        }

    @get:Bindable
    var commentsSectionExpended: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.commentsSectionExpended)
        }

    fun expendComments() {
        commentsSectionExpended = !commentsSectionExpended
    }

    fun loadData() {
        showReloadButton = false
        loadingInProgress = true

        postId?.let { postId ->
            postUseCase
                .getPostById(postId)
                .doOnError {  }
                .toObservable()
                .flatMap(
                    { post -> postUseCase.getUserById(post.userId!!).toObservable() },
                    { post: Post, user: User -> Pair(post, user) }
                )
                .firstOrError()
                .doOnSuccess { result ->
                    setPostData(result.first)
                    postAuthor = result.second.username!!
                }
                .doOnError {
                    showReloadButton = true
                }
                .doFinally {
                    loadingInProgress = false
                }
                .withNetworkSchedulers(rxSchedulers)
                .addTo(compositeDisposable)


            postUseCase
                .getCommentsByPostId(postId)
                .withNetworkSchedulers(rxSchedulers)
                .doOnSuccess { result ->
                    setComments(result)
                }
                .addTo(compositeDisposable)
        }

    }


    private fun setComments(comments: List<Comment>) {
        commentListLiveData.value = comments
        commentSectionVisibility = comments.isNotEmpty()
        commentsCount = comments.size.toString()
    }

    private fun setPostData(post: Post) {
        postTitle = post.title
        postBody = post.body
    }

}