package io.jonibek.feedster.util

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageButton
import io.jonibek.feedster.R

class CustomBindingAdapters {

    companion object {

        @BindingAdapter("android:visibility")
        @JvmStatic
        fun setVisibility(view: View, visibility: Boolean) {
            view.visibility = if (visibility) View.VISIBLE else View.GONE
        }

        @BindingAdapter("android:isExpanded")
        @JvmStatic
        fun setExpensionState(imageButton: ImageButton, isExpended: Boolean) {
            if(isExpended){
                imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_expand_more_black_36dp))
            }else{
                imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_expand_less_black_36dp))
            }
        }
    }

}