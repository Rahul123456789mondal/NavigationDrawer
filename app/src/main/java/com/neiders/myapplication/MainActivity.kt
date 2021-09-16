package com.neiders.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.neiders.myapplication.ui.gallery.GalleryFragment
import com.neiders.myapplication.ui.home.HomeFragment
import com.neiders.myapplication.ui.slideshow.SlideshowFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private var currentTab = ""
    private var mDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_icon)

        mDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(mDrawerToggle!!)

        displayFragment(TAB_HOME!!)
        currentTab = TAB_HOME


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDrawerToggle!!.syncState()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                true
            }
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                openHome()
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_gallery -> {
                openGallery()
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_slideshow -> {
                openSlideShow()
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_test -> {
                opentest()
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openHome() {
        if (currentTab == TAB_HOME) {
            return
        }
        removeCurrentFragment()
        displayFragment(TAB_HOME!!)
        currentTab = TAB_HOME
    }

    private fun openGallery() {
        if (currentTab == TAB_GALLERY) {
            return
        }
        removeCurrentFragment()
        displayFragment(TAB_GALLERY!!)
        currentTab = TAB_GALLERY
    }

    private fun openSlideShow() {
        if (currentTab == TAB_SLIDESHOW) {
            return
        }
        removeCurrentFragment()
        displayFragment(TAB_SLIDESHOW!!)
        currentTab = TAB_SLIDESHOW
    }

    private fun opentest() {
        if (currentTab == TAB_TEST) {
            return
        }
        removeCurrentFragment()
        displayFragment(TAB_TEST!!)
        currentTab = TAB_TEST
    }

    private fun displayFragment(tag: String) {
        var fragment: Fragment? = findFragmentByTag(tag)
        if (fragment != null) {
            showFragment(fragment)
        } else {
            fragment = supportFragmentManager.fragmentFactory.instantiate(
                ClassLoader.getSystemClassLoader(), tag
            )
            addFragment(fragment, tag)
        }
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        getFragmentTransaction().add(
            R.id.mainFrameLayout,
            fragment, tag
        ).commitAllowingStateLoss()
    }

    private fun showFragment(fragment: Fragment) {
        getFragmentTransaction().show(fragment).commitAllowingStateLoss()
    }

    private fun findFragmentByTag(tag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }

    private fun removeCurrentFragment() {
        val fragment: Fragment = getDisplayedFragment()
        getFragmentTransaction().remove(fragment).commitAllowingStateLoss()
    }

    private fun getDisplayedFragment(): Fragment {
        return supportFragmentManager.findFragmentById(R.id.mainFrameLayout) as Fragment
    }

    private fun getFragmentTransaction(): FragmentTransaction {
        return supportFragmentManager.beginTransaction()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            val fragment: Fragment = getDisplayedFragment()
            if (fragment.isVisible) {
                if (fragment is HomeFragment) {
                    exitFromApp()
                } else if (fragment is GalleryFragment
                    || fragment is SlideshowFragment
                ) {
                    openHome()
                } else {
                    super.onBackPressed()
                }
            } else {
                super.onBackPressed()
            }
        }
    }

    private fun exitFromApp() {
        super.onBackPressed()
    }

    companion object {
        private val TAB_HOME: String? = HomeFragment::class.java.canonicalName
        private val TAB_GALLERY: String? = GalleryFragment::class.java.canonicalName
        private val TAB_SLIDESHOW: String? = SlideshowFragment::class.java.canonicalName
        private val TAB_TEST: String? = TestFragment::class.java.canonicalName

    }
}