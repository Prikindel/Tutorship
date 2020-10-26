package com.prike.tutorship.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prike.tutorship.R

class TimetableWeekAdapterList(private val lessons: ArrayList<Any>) : RecyclerView.Adapter<TimetableWeekAdapterList.TimetableWeekViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableWeekViewHolder {
        return TimetableWeekViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.timetable_week_view_for_list, parent, false))
    }

    override fun onBindViewHolder(holder: TimetableWeekViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = lessons.size

    //возвращает позицию элемента в списке
    fun getItem(position: Int): Any {
        return lessons[position]
    }

    //функция добавления одного элемента
    fun add(newItem: Any) {
        lessons.add(newItem)
    }

    //функция добавления всех элементов
    fun add(newItems: List<Any>) {
        lessons.addAll(newItems)
    }

    class TimetableWeekViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Any) {

        }
    }
}