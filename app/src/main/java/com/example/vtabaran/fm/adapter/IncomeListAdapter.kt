package com.example.vtabaran.fm.adapter

import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.vtabaran.fm.MainActivity
import com.example.vtabaran.fm.R
import com.example.vtabaran.fm.entity.Income

class IncomeListAdapter(private val inflater: LayoutInflater, private var container: LinearLayout) {
    fun append(incomes: List<Income>) {
 //        incomes.sortBy { income ->
//            income.incomeAt
//        }


        for (income in incomes) {
            val groupedIncomes = inflater.inflate(R.layout.income_list_date_grouped, null)
            groupedIncomes.findViewById<TextView>(R.id.income_list_date).text = income.incomeAt.toString()

            val incomesList = inflater.inflate(R.layout.income_list, null)
            incomesList.findViewById<TextView>(R.id.income_value).text = income.value.toString()

            groupedIncomes.findViewById<LinearLayout>(R.id.income_list_by_date).addView(incomesList)

            container.addView(groupedIncomes)
        }

        for (income in incomes) {
            val groupedIncomes = inflater.inflate(R.layout.income_list_date_grouped, null)
            groupedIncomes.findViewById<TextView>(R.id.income_list_date).text = income.incomeAt.toString()

            val incomesList = inflater.inflate(R.layout.income_list, null)
            incomesList.findViewById<TextView>(R.id.income_value).text = income.value.toString()

            groupedIncomes.findViewById<LinearLayout>(R.id.income_list_by_date).addView(incomesList)

            container.addView(groupedIncomes)
        }

        for (income in incomes) {
            val groupedIncomes = inflater.inflate(R.layout.income_list_date_grouped, null)
            groupedIncomes.findViewById<TextView>(R.id.income_list_date).text = income.incomeAt.toString()

            val incomesList = inflater.inflate(R.layout.income_list, null)
            incomesList.findViewById<TextView>(R.id.income_value).text = income.value.toString()

            groupedIncomes.findViewById<LinearLayout>(R.id.income_list_by_date).addView(incomesList)

            container.addView(groupedIncomes)
        }

    }

    companion object {
        lateinit var mainActivity: MainActivity

        fun init(activity: MainActivity) {
            mainActivity = activity
        }
    }
}