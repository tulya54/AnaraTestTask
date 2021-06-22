package com.kmf.anaratesttask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kmf.anaratesttask.R

class StatusFragment: BottomSheetDialogFragment() {

    companion object {
        private const val TAG = "StatusFragment"
    }

    interface OnItemLickListener {
        fun onItemClick(index: Int)
    }

    private var listener: OnItemLickListener? = null
    fun setOnItemClickListener(listener: OnItemLickListener?) {
        this.listener = listener
    }

    override fun onCreateView(infl: LayoutInflater, parent: ViewGroup?, bundle: Bundle?): View? =
        infl.inflate(R.layout.fragment_status, parent, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = initViews(view)

    private fun initViews(view: View) {
        view.findViewById<TextView>(R.id.tvIndex1)
            .setOnClickListener {
                onItemClick(1)
            }
        view.findViewById<TextView>(R.id.tvIndex2)
            .setOnClickListener {
                onItemClick(2)
            }
        view.findViewById<TextView>(R.id.tvIndex3)
            .setOnClickListener {
                onItemClick(3)
            }
        view.findViewById<TextView>(R.id.tvIndex4)
            .setOnClickListener {
                onItemClick(4)
            }
        view.findViewById<TextView>(R.id.tvIndex5)
            .setOnClickListener {
                onItemClick(5)
            }
    }

    private fun onItemClick(index: Int) {
        listener?.onItemClick(index)
        dismiss()
    }
}