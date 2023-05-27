package com.example.practice3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.practice3.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(FragmentNews(), shouldAddToBackStack = false)
    }
}

fun FragmentActivity.addFragment(fragment: Fragment, shouldAddToBackStack: Boolean = true) {
    val tag = fragment.javaClass.simpleName

    supportFragmentManager.beginTransaction().apply {
        add(R.id.container, fragment, tag)
        if (shouldAddToBackStack) {
            addToBackStack(tag)
        }
        commit()
    }
}