package com.omarrujo.randomperson.viewmodel


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omarrujo.randomperson.R
import com.omarrujo.randomperson.model.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.omarrujo.randomperson.model.RemoteDatabase

class RandomUserViewModel(applicationContext: Context) : ViewModel() {
    private val remoteDatabase = RemoteDatabase()
    private val _userData = MutableLiveData<UserData>()
    val personMessage = applicationContext.getString(R.string.person)

    val userData: LiveData<UserData>
        get() = _userData

    private val _caption = MutableLiveData<String>()
    val caption: LiveData<String> get() = _caption

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    //CHECk's
    val cbPerson = MutableLiveData<Boolean>(true)
    val cbEmail = MutableLiveData<Boolean>(false)
    val cbBithday = MutableLiveData<Boolean>(false)
    val cbAdress = MutableLiveData<Boolean>(false)
    val cbPhone = MutableLiveData<Boolean>(false)
    val cbPassword = MutableLiveData<Boolean>(false)

    fun fetchRandomUser() {

        remoteDatabase.service.getRandomUser().enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if (response.isSuccessful) {
                    _userData.value = response.body()

                    setPersonInformation(
                        cap = personMessage,
                        tit = "${_userData.value!!.results[0].name.first} ${_userData.value!!.results[0].name.last}",
                        cbPerson
                    )
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                _title.value = "INTENTALO DE NUEVO"
            }
        })
    }
    fun setPersonInformation( cap: String, tit: String,checkedBox: MutableLiveData<Boolean> ) {
        _caption.value = cap
        _title.value = tit
         setCorrectCheck( checkedBox )
    }

    private fun setCorrectCheck(checkedBox: MutableLiveData<Boolean>){
         cbPerson.value = false
         cbEmail.value = false
         cbBithday.value = false
         cbAdress.value = false
         cbPhone.value = false
         cbPassword.value = false
         checkedBox.value = true
    }
}
