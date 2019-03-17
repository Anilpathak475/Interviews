package com.test.todo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appify.network.models.Todo
import com.test.todo.R
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoRecyclerViewAdapter(private val items: ArrayList<Todo>, val listener: Listener) : RecyclerView.Adapter<ToDoRecyclerViewAdapter.TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {


        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        if (items[position].taskStatus == "completed") {
            holder.checked.isChecked = true
        }
        holder.assignedTask.text = items[position].assignedTask
        holder.checkedStatus.text = items[position].taskStatus
    }


    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checked: CheckBox = view.checkBox
        val assignedTask: TextView = view.task
        val checkedStatus: TextView = view.status
    }

    interface Listener {
        fun onChildClick() {}
    }
}