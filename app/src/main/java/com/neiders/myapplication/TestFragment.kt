package com.neiders.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.neiders.myapplication.databinding.FragmentTestBinding

class TestFragment : Fragment() {

    private lateinit var testBinding : FragmentTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Fragment", "OnCreate()")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        testBinding = FragmentTestBinding.inflate(layoutInflater)
        Log.d("Fragment", "OnCreateView()")
        return testBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Fragment", "OnViewCreated()")
        testBinding.button.setOnClickListener {

            val fragmentManager : FragmentManager =  requireActivity().supportFragmentManager
            val transaction : FragmentTransaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fragmentContainerView, TesingFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("Fragment", "onViewStateRestored()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Fragment", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Fragment", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Fragment", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Fragment", "onStop()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Fragment", "onSaveInstanceState()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Fragment", "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Fragment", "onDestroy()")
    }

}