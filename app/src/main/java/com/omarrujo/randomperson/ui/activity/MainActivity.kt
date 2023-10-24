package com.omarrujo.randomperson.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.omarrujo.randomperson.databinding.ActivityMainBinding
import com.omarrujo.randomperson.viewmodel.RandomUserViewModel
import com.omarrujo.randomperson.viewmodel.RandomUserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit  var randomUserViewModel: RandomUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView(binding.root)

//        randomUserViewModel = ViewModelProvider(this)[RandomUserViewModel::class.java]
        randomUserViewModel = ViewModelProvider(this, RandomUserViewModelFactory(applicationContext)).get(RandomUserViewModel::class.java)
        binding.viewModel = randomUserViewModel
        binding.lifecycleOwner = this


        randomUserViewModel.fetchRandomUser()

        randomUserViewModel.userData.observe(this, Observer { userData ->
            Glide.with(this)
                .load(userData.results[0].picture.large)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageView)
        })
        }
}