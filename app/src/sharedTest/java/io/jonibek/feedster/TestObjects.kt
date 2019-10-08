package io.jonibek.feedster

import com.google.gson.Gson
import io.jonibek.feedster.data.entities.Comment
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.data.entities.User
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class TestObjects {

    companion object {

        fun getNumbers() : Map<String,String>{
            val json = readFromFile("numbers.json")
            return Gson().fromJson(json, mutableMapOf<String,String>()::class.java)
        }

        fun getPostList(): List<Post> {
            val json = readFromFile("posts.json")
            return Gson().fromJson(json, Array<Post>::class.java).toList()
        }

        fun getPost(): Post {
            val json = readFromFile("post.json")
            return Gson().fromJson(json, Post::class.java)
        }

        fun getUser(): User {
            val json = readFromFile("user.json")
            return Gson().fromJson(json, User::class.java)
        }

        fun getComments(): List<Comment> {
            val json = readFromFile("comments.json")
            return Gson().fromJson(json, Array<Comment>::class.java).toList()
        }

        @Throws(IOException::class)
        fun readFromFile(filename: String): String {
            val inputStream = TestObjects::class.java.classLoader!!.getResourceAsStream(filename)
            val br = BufferedReader(InputStreamReader(inputStream))
            val sb = StringBuilder()
            var line: String? = br.readLine()
            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }

            return sb.toString()
        }
    }
}