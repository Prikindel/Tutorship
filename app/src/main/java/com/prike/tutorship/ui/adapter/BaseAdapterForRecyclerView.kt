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
abstract class BaseAdapterForRecyclerView<Type : Any, VH : BaseAdapterForRecyclerView.BaseViewHolder<Type>> : RecyclerView.Adapter<VH>() {

    private val items: MutableList<Type> = arrayListOf()

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
    fun add(newItem: Type) {
        items.add(newItem)
        notifyItemInserted(items.size - 1)
    }

    /**
     * Добавление списка элементов
     *
     * @param newItems список элементов
     */
    fun add(newItems: List<Type>) {
        val pos1 = items.size - 1
        items.addAll(newItems)
        notifyItemRangeInserted(pos1, items.size - 1)
    }

    /**
     * Добавление списка элементов [newItems] начиная с [start]
     *
     * @param newItems список элементов
     * @param start начальная позиция вставки
     */
    fun add(newItems: List<Type>, start: Int) {
        items.addAll(start, newItems)
        notifyItemRangeInserted(start, newItems.size + start)
    }

    /**
     * Добавление элемента по позиции
     *
     * @param newItem новый элемент
     * @param position позиция
     */
    fun add(newItem: Type, position: Int) {
        items.add(position, newItem)
        notifyItemInserted(position)
    }

    /**
     * Добавление нового элемента в начало списка
     *
     * @param newItem новый элемент
     */
    fun addStart(newItem: Type) {
        add(newItem, 0)
    }

    /**
     * Добавление нового элемента в конец списка
     *
     * @param newItem новый элемент
     */
    fun addEnd(newItem: Type) {
        add(newItem)
    }

    /**
     * Обновление списка
     *
     * @param newItems новый список элементов
     */
    fun update(newItems: List<Type>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    /**
     * Обновление элемента по его позиции
     *
     * @param newItem элемент
     * @param position позиция
     */
    fun update(newItem: Type, position: Int) {
        items[position] = newItem
        notifyItemChanged(position)
    }

    /**
     * Обновление списка элементов [newItems] начиная с позиции [start]
     *
     * @param newItems список новых элементов
     * @param start позиция старта обновления
     */
    fun update(newItems: List<Type>, start: Int) {
        for (position in start until newItems.size) {
            if (position < items.size) {
                items[position] = newItems[position - start]
                notifyItemChanged(position)
            } else {
                add(newItems.subList(position, newItems.size - 1))
                break
            }
        }
    }

    /**
     * Удаляет элемент c позиции [position]
     *
     * @param position
     */
    fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * Удаляет элемент
     *
     * @param item элемент
     */
    fun remove(item: Type) {
        val position = items.indexOf(item)
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * Очищает список
     *
     */
    fun clear() {
        val end = items.size - 1
        items.clear()
        notifyItemRangeRemoved(0, end)
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