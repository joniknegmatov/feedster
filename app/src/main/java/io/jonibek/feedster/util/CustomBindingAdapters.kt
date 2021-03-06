package io.jonibek.feedster.util

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageButton
import io.jonibek.feedster.R


@BindingAdapter("visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("isExpanded")
fun setExpensionState(imageButton: ImageButton, isExpended: Boolean) {
    if (isExpended) {
        imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_expand_more_black_36dp))
    } else {
        imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_expand_less_black_36dp))
    }
}

@BindingAdapter("isFavorite")
fun setFavoriteState(imageButton: ImageButton, isFavorite: Boolean) {
    if (isFavorite) {
        imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_heart))
    } else {
        imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_heart_outline))
    }
}
