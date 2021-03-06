package com.example.vtabaran.fm.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vtabaran.fm.R
import com.example.vtabaran.fm.adapter.IncomeListAdapter
import com.example.vtabaran.fm.entity.Income
import com.example.vtabaran.fm.service.repository.IncomeRepository


class IncomeFragment : Fragment() {

    private var incomeOffset = 0
    private val incomeCount = 10
    private lateinit var incomeAdapter: IncomeListAdapter

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_income, container, false)

        incomeAdapter = IncomeListAdapter(inflater, view.findViewById(R.id.income_list_container))
        loadMoreIncomes()

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    private fun loadMoreIncomes() {
        val incomeRepository = IncomeRepository()
        val incomes = incomeRepository.findByUser(incomeOffset, incomeCount)
        incomeAdapter.append(incomes)
        incomeOffset += incomeCount
    }

    companion object {

        fun newInstance(): IncomeFragment {
            return IncomeFragment()
        }

    }
}
