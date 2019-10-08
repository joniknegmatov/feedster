package io.jonibek.feedster.test

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.jonibek.feedster.TestObjects
import org.junit.Test

class Converter {

    @Test
    fun convertToUsers(){
        val numbers = TestObjects.getNumbers()
        val userList = mutableListOf<User>()
        numbers.forEach{
            userList.add(User(name = it.value,number =  it.key))
        }

        val json = Gson().toJson(userList)
        json.length
    }

}

data class User( @SerializedName("Name")
                 val name : String? = null,
                 @SerializedName("Number")
                 val number : String? = null){

    companion object{
        fun fromPair(pair : Pair<String,String>) : User{
            return User(pair.first,pair.second)
        }
    }
}