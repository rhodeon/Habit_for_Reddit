package com.rhodeon.habitforreddit.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.rhodeon.habitforreddit.R

/**
 * Created by Ruona Onobrakpeya on 1/3/21.
 */

/**
 * Returns the child fragment with the specified tag in a viewpager.
 * ViewPager2 fragment tags are in the pattern f0, f1, f2, ...
 * Hmm. Let's see how long till Google changes this.
 */
@Suppress("UNCHECKED_CAST")
fun <T : Fragment> Fragment.viewPagerFragment(position: Int): T {
    val viewPagerFragment = childFragmentManager.findFragmentByTag("f$position")!!
    return viewPagerFragment as T
}

/**
 * Prevents the app from crashing if a navigation event is initiated while the fragment hasn't
 * fully transitioned.
 */
fun Fragment.navigateSafe(direction: NavDirections) {
    val navController = findNavController()
    val navControllerDestinationId = navController.currentDestination?.id

    // Get currently saved id
    val initialFragmentDestinationId = view?.getTag(R.id.tag_navigation_destination_id) ?: navControllerDestinationId

    // Check that the navigation graph is still in 'this' fragment, if so then navigate:
    if (navControllerDestinationId == initialFragmentDestinationId) {
        view?.setTag(R.id.tag_navigation_destination_id, initialFragmentDestinationId)  // Save destination id to check against next
        navController.navigate(direction)
    }
}