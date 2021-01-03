package com.rhodeon.habitforreddit.utils

import androidx.fragment.app.Fragment

/**
 * Created by Ruona Onobrakpeya on 1/3/21.
 */

/**
 * Returns the child fragment with the specified tag in a viewpager.
 * ViewPager2 fragment tags are in the pattern f0, f1, f2, ...
 * Hmm. Let's see how long till Google changes this.
 */
@Suppress("UNCHECKED_CAST")
fun <T: Fragment>Fragment.viewPagerFragment(position: Int): T {
    val viewPagerFragment = childFragmentManager.findFragmentByTag("f$position")!!
    return viewPagerFragment as T
}

