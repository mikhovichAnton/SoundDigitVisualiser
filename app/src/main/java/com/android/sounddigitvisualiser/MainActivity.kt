package com.android.sounddigitvisualiser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.sounddigitvisualiser.presentation.fragment.imageviewanimation.ViewAnimatorFragment
import com.android.sounddigitvisualiser.databinding.ActivityMainBinding
import com.android.sounddigitvisualiser.domain.repository.local.navigation.FragmentNavigation
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}