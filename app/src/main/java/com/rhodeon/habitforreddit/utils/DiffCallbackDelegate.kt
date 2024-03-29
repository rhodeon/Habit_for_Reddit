package com.rhodeon.habitforreddit.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import kotlin.reflect.KProperty

/**
 * Created by Ruona Onobrakpeya on 12/16/20.
 */

/**
 * Simplifies the need to implement areItemsTheSame and areContentsTheSame on every item callback.
 */
class DiffCallbackDelegate<T> {
    operator fun getValue(thisRef: Any, property: KProperty<*>): DiffUtil.ItemCallback<T> {
        return object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                return oldItem === newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                return oldItem == newItem
            }
        }
    }
}


