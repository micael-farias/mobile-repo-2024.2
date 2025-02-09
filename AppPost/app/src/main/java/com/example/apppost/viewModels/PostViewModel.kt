package com.example.apppost.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppost.data.RetrofitInstance
import com.example.apppost.data.models.CreatePostRequest
import com.example.apppost.data.models.Post
import com.example.apppost.data.models.User
import com.example.apppost.data.models.UserCreateRequest
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    var posts: List<Post> by mutableStateOf(listOf())
    var users: List<User> by mutableStateOf(listOf())
    private val userId = 1

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                users = RetrofitInstance.api.getUsers()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                posts = RetrofitInstance.api.getPosts(userId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createUser(
        name: String,
        email: String,
        onSucess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                val newUser = UserCreateRequest(name, email)
                RetrofitInstance.api.createUser(newUser)
                fetchUsers()
                onSucess()
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }
    }

    fun createPost(
        title: String,
        content: String,
        onSucess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                if (!title.isNotBlank() && !content.isNotBlank()) {
                    onError()
                } else {
                    val newPost = CreatePostRequest(title, content)
                    RetrofitInstance.api.createPost(userId, newPost)
                    fetchPosts()
                    onSucess()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }
    }

    fun deletePost(postId: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deletePost(postId)
                fetchPosts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updatePost(
        postId: Int,
        title: String,
        content: String
    ) {
        viewModelScope.launch {
            try {
                val updatedPost = CreatePostRequest(title, content)
                RetrofitInstance.api.updatePost(postId, updatedPost)
                fetchPosts()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}