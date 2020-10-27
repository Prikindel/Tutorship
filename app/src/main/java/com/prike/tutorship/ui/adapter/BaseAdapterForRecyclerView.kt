package com.prike.tutorship.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Абстрактрный, базовый класс адаптера для списков, содержащий базовые методы добавления, получения элементов.
 * А также базовый ViewHolder
 *
 * @param Type тип элементов списка
 * @param VH ViewHolder
 * @property items список элементов
 */
abstract class BaseAdapterForRecyclerView<Type : Any, VH : BaseAdapterForRecyclerView.BaseViewHolder<Type>> (
    private val items: MutableList<Type>
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
    fun add(newitem: Type) = items.add(newitem)

    /**
     * Добавление списка элементов
     *
     * @param newItems список элементов
     */
    fun add(newItems: List<Type>) = items.addAll(newItems)

    /**
     * Обновление списка
     *
     * @param newItems новый список элементов
     */
    fun update(newItems: List<Type>) {
        items.clear()
        items.addAll(newItems)
    }

    /**
     * Абстрактный класс ViewHolder
     *
     * @param Type тип элемента
     * @property view
     */
    abstract class BaseViewHolder<Type : Any> (
        protected val view: View
    ) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: Type)
    }
}