package com.chocolate_team.todo_application.ui.model


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chocolate_team.todo_application.R
import com.chocolate_team.todo_application.databinding.ItemRowBinding


class ItemHolderView(view: View) : RecyclerView.ViewHolder(view) {
    var binding=ItemRowBinding.bind(view)

}
