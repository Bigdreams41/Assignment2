package com.example.assignment2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment2.model.MusicDetail
import com.example.assignment2.model.MusicItem
import com.example.assignment2.model.remote.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MusicViewModel"
class MusicViewModel: ViewModel() {

    private var _resultCount: Int = 0
    private val _musicSearchResult = MutableLiveData<List<MusicItem>>()

    val musicSearchResult: LiveData<List<MusicItem>>
        get() = _musicSearchResult


    private val _errorMessages = MutableLiveData("")
    val errorMessages: LiveData<String>
        get() = _errorMessages

    var resultCount: Int
    get() = _resultCount
    set(value) {_resultCount = value}


    fun itunesMusic(term: String) {
        API.musicApi.queryItunesMusic(term)
            .enqueue(
                object : Callback<MusicDetail> {
                    override fun onResponse(
                        call: Call<MusicDetail>,
                        response: Response<MusicDetail>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("testBody", "onResponse:${response.body()!!} ")
                            response.body()?.let {
                                Log.d(TAG, "onResponse: $it ")
                                _musicSearchResult.value = it.results
                                _resultCount = it.resultCount
                            } ?: kotlin.run {
                                _errorMessages.value = response.message()
                            }
                        } else {
                            _errorMessages.value = response.message()
                        }
                    }

                    override fun onFailure(call: Call<MusicDetail>, t: Throwable) {
                        t.printStackTrace()
                        _errorMessages.value = t.message ?: "Unknown error"
                    }
                }
            )
    }
}


