package io.jonibek.feedster.ui.post

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.Bindable
import io.jonibek.feedster.BR
import io.jonibek.feedster.data.pojo.Comment
import io.jonibek.feedster.data.pojo.Post
import io.jonibek.feedster.data.pojo.User
import io.jonibek.feedster.domain.BaseUseCase
import io.jonibek.feedster.domain.post.PostUseCase
import io.jonibek.feedster.ui.ObservableViewModel
import javax.inject.Inject

class PostFragmentViewModel(private val postUseCase: PostUseCase) : ObservableViewModel() {

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
    var commentsCount : String = ""
    set(value) {
        field = "($value)"
        notifyPropertyChanged(BR.commentsCount)
    }

    @get:Bindable
    var commentSectionVisiblity : Boolean = false
    set(value) {
        field = value
        notifyPropertyChanged(BR.commentSectionVisiblity)
    }

    @get:Bindable
    var commentsSectionExpended : Boolean = false
    set(value) {
        field = value
        notifyPropertyChanged(BR.commentsSectionExpended)
    }

    fun expendComments(){
        commentsSectionExpended = !commentsSectionExpended
    }

    fun loadData() {
        showReloadButton = false
        loadingInProgress = true

        postId?.let { id ->
            postUseCase.getPostById(id, object : BaseUseCase.Callback<Post> {

                override fun onResult(result: Post) {
                    loadingInProgress = false
                    result.userId?.let {
                        loadUserData(it)
                    }

                    setPostData(result)
                }

                override fun onFailure(e: Throwable) {
                    loadingInProgress = false
                    showReloadButton = true
                }
            })

            postUseCase.getCommentsByPostId(id, object : BaseUseCase.Callback<List<Comment>> {
                override fun onResult(result: List<Comment>) {
                    setComments(result)
                }

                override fun onFailure(e: Throwable) {
                    //TODO IMPLEMENT ON ERROR CASE
                }

            })
        }

    }

    private fun setComments(comments : List<Comment>){
        commentListLiveData.value = comments
        commentSectionVisiblity = comments.isNotEmpty()
        commentsCount = comments.size.toString()
    }

    private fun setPostData(post: Post) {
        postTitle = post.title
        postBody = post.body
    }

    fun loadUserData(userId: Int) {
        postUseCase.getUserById(userId, object : BaseUseCase.Callback<User> {

            override fun onResult(result: User) {
                postAuthor = result.username!!
            }

            override fun onFailure(e: Throwable) {
                //TODO IMPLEMENT ON ERROR CASE
            }

        })
    }

    class Factory @Inject constructor(private val postUseCase: PostUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PostFragmentViewModel(postUseCase) as T
        }
    }
}