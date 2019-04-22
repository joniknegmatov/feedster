package io.jonibek.feedster


import com.google.gson.Gson
import io.jonibek.feedster.data.pojo.Comment
import io.jonibek.feedster.data.pojo.Post
import io.jonibek.feedster.data.pojo.User


class TestObjects {

    companion object {

        fun getPostList(): List<Post> {
            val json = TestObjReader().readFromFile("posts.json")
            return Gson().fromJson(json, Array<Post>::class.java).toList()
        }

        fun getPost(): Post {
            val json = TestObjReader().readFromFile("post.json")
            return Gson().fromJson(json, Post::class.java)
        }

        fun getUser(): User {
            val json = TestObjReader().readFromFile("user.json")
            return Gson().fromJson(json, User::class.java)
        }

        fun getComments(): List<Comment> {
            val json = TestObjReader().readFromFile("comments.json")
            return Gson().fromJson(json, Array<Comment>::class.java).toList()
        }
    }
}