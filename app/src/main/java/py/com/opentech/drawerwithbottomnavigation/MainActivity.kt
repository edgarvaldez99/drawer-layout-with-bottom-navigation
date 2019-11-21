package py.com.opentech.drawerwithbottomnavigation

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.bottom_navigation_view.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupNavController()
        setupDrawerNavigation()
        setupBottomNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupNavController() {
        navController = findNavController(R.id.nav_host_fragment)
    }

    private fun setupDrawerNavigation() {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                        R.id.nav_tools, R.id.nav_share, R.id.nav_send
                ), drawer_layout //Note that we use kotlinx.android.synthetic to get the drawerLayout reference in the xml
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Note that we use kotlinx.android.synthetic to get the NavigationView reference in the xml
        nav_view.setupWithNavController(navController)
        // Add this listener to the DrawerLayout because besides navigating to the
        // corresponding view we also need to mark the bottom button as checked
        nav_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    // mark the bottom button as checked
                    nav_home_button.isChecked = true
                    navigateTo(R.id.nav_home)
                }
                R.id.nav_gallery -> {
                    nav_gallery_button.isChecked = true
                    navigateTo(R.id.nav_gallery)
                }
                R.id.nav_slideshow -> {
                    nav_slideshow_button.isChecked = true
                    navigateTo(R.id.nav_slideshow)
                }
                R.id.nav_tools -> {
                    nav_tools_button.isChecked = true
                    navigateTo(R.id.nav_tools)
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setupBottomNavigation() {
        nav_home_button.setOnClickListener {
            navigateTo(R.id.nav_home)
        }
        nav_gallery_button.setOnClickListener {
            navigateTo(R.id.nav_gallery)
        }
        nav_slideshow_button.setOnClickListener {
            navigateTo(R.id.nav_slideshow)
        }
        nav_tools_button.setOnClickListener {
            navigateTo(R.id.nav_tools)
        }
    }

    private fun navigateTo(resId: Int) {
        navController.navigate(resId)
    }
}
