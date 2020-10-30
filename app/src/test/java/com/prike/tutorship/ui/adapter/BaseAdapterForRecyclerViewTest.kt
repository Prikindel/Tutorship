package com.prike.tutorship.ui.adapter

import android.view.View
import android.view.ViewGroup
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class BaseAdapterForRecyclerViewTest {

    class TestBaseAdapter : BaseAdapterForRecyclerView<Int, TestBaseAdapter.TestViewHolder>() {
        class TestViewHolder(view: View) : BaseAdapterForRecyclerView.BaseViewHolder<Int>(view) {
            override fun bind(item: Int) {
                TODO("Not yet implemented")
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
            TODO("Not yet implemented")
        }
    }

    val testAd = TestBaseAdapter()

    @Before
    fun setUp() {
        testAd.update(arrayListOf(1, 2, 3))
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getItem() {
    }

    @Test
    fun add() {
    }

    @Test
    fun testAdd() {
    }

    @Test
    fun testAdd1() {
    }

    @Test
    fun testAdd2() {
    }

    @Test
    fun addStart() {
    }

    @Test
    fun addEnd() {
    }

    @Test
    fun update() {
    }

    @Test
    fun testUpdate() {
    }

    @Test
    fun testUpdate1() {
    }

    @Test
    fun remove() {
    }

    @Test
    fun testRemove() {
    }

    @Test
    fun clear() {
    }
}