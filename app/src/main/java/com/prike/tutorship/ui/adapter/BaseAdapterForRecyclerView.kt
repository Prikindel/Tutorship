package com.prike.tutorship.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Абстрактрный, базовый класс адаптера для списков, содержащий базовые методы добавления, получения элементов.
 * А также базовый ViewHolder
 *
 * @param VH ViewHolder
 * @property items список элементов
 */
abstract class BaseAdapterForRecyclerView<VH : BaseAdapterForRecyclerView.BaseViewHolder> (
    private val items: MutableList<Any>
) : RecyclerView.Adapter<VH>() {

    /**
     * Возвращает длину списка
     *
     */
    override fun getItemCount() = items.size

    /**
     * Связывает views с содержимым
     *
     * @param holder ViewHolder
     * @param position позиция элемента
     */
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Возвращает элемент по его позиции в списке
     *
     * @param position позиция элемента в списке
     */
    fun getItem(position: Int) = items[position]

    /**
     * Добавление одного элемента
     *
     * @param newitem элемент
     */
    fun add(newitem: Any) = items.add(newitem)

    /**
     * Добавление списка элементов
     *
     * @param newItems список элементов
     */
    fun add(newItems: List<Any>) = items.addAll(newItems)

    /**
     * Абстрактный класс ViewHolder
     *
     * @property view
     */
    abstract class BaseViewHolder(protected val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: Any)
    }
}