package io.jonibek.feedster.util

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageButton
import io.jonibek.feedster.R

class CustomBindingAdapters {

    companion object {

        @BindingAdapter("app:visibility")
        @JvmStatic
        fun setVisibility(view: View, visibility: Boolean) {
            view.visibility = if (visibility) View.VISIBLE else View.GONE
        }

        @BindingAdapter("app:isExpanded")
        @JvmStatic
        fun setExpensionState(imageButton: ImageButton, isExpended: Boolean) {
            if(isExpended){
                imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_expand_more_black_36dp))
            }else{
                imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_expand_less_black_36dp))
            }
        }

        @BindingAdapter("app:isFavorite")
        @JvmStatic
        fun setFavoriteState(imageButton: ImageButton, isFavorite: Boolean) {
            if(isFavorite){
                imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_heart))
            }else{
                imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_heart_outline))
            }
        }
    }

}