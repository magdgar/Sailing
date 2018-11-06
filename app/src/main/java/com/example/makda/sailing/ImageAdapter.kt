package com.example.makda.sailing

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

private val mThumbIds = arrayOf<Int>(
    R.drawable.sample_2, R.drawable.sample_3,
    R.drawable.sample_4, R.drawable.sample_5)

class ImageAdapter(private val mContext: Context) : BaseAdapter() {

    override fun getCount(): Int = mThumbIds.size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0L

    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = ImageView(mContext)
            imageView.layoutParams = ViewGroup.LayoutParams(150, 150)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(2, 2, 2, 2)
        } else {
            imageView = convertView as ImageView
        }

        imageView.setImageResource(mThumbIds[position])
        return imageView
    }
}