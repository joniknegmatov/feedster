package io.jonibek


import com.google.gson.Gson
import io.jonibek.feedster.data.entities.Comment
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.data.entities.User
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader


class TestObjects {

    companion object {
        val ASSET_BASE_PATH = "../app/sampledata/"

        @Throws(IOException::class)
        fun readJsonFile(filename: String): String {
            val br = BufferedReader(InputStreamReader(FileInputStream(ASSET_BASE_PATH + filename)))
            val sb = StringBuilder()
            var line: String? = br.readLine()
            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }

            return sb.toString()
        }


        fun getPostList(): List<Post> {
            val json = readJsonFile("posts.json")
            return Gson().fromJson(json, Array<Post>::class.java).toList()
        }

        fun getPost(): Post {
            val json = readJsonFile("post.json")
            return Gson().fromJson(json, Post::class.java)
        }

        fun getUser(): User {
            val json = readJsonFile("user.json")
            return Gson().fromJson(json, User::class.java)
        }

        fun getComments(): List<Comment> {
            val json = readJsonFile("comments.json")
            return Gson().fromJson(json, Array<Comment>::class.java).toList()
        }
    }
}