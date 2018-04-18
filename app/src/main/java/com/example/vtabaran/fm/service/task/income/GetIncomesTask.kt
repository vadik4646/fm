package com.example.vtabaran.fm.service.task.income

import android.os.AsyncTask
import android.util.Log
import com.example.vtabaran.fm.User
import com.example.vtabaran.fm.service.api.ApiRequest
import com.example.vtabaran.fm.service.api.Response
import com.example.vtabaran.fm.service.api.request.income.GetIncomesRequest
import com.example.vtabaran.fm.entity.Category
import com.example.vtabaran.fm.entity.Currency
import com.example.vtabaran.fm.entity.Income
import com.example.vtabaran.fm.entity.Tag
import com.example.vtabaran.fm.fragment.IncomeFragment
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.text.SimpleDateFormat
import java.util.*

class GetIncomesTask constructor(private var getIncomeRequest: GetIncomesRequest, private val fragment: IncomeFragment) : AsyncTask<String, String, Response?>() {

    override fun doInBackground(vararg params: String): Response? {
        try {
            val request = ApiRequest(getIncomeRequest)

            return request.send()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    override fun onPostExecute(response: Response?) {
        if (response == null || response.hasErrors()) {
            Log.d("GetIncomesTask", response?.getMessage() ?: "Empty response")
            return
        }

        val token: String = response.getToken() ?: return
        val user = User.get()
        if (user != null) {
            User.set(user.email, token, user.id)
        }

        val incomes = buildIncomes(response)
//        fragment.displayIncomes(incomes)
    }

    private fun buildIncomes(response: Response?): Collection<Income> {
        val incomes: MutableCollection<Income> = mutableListOf()
        if (response == null) {
            return incomes
        }

        val rawIncomes = response.getData()?.getJSONArray("incomes") ?: return incomes

        (0..(rawIncomes.length() - 1))
                .map { rawIncomes.getJSONObject(it) }.mapTo(incomes) { it -> buildIncome(it) }

        return incomes
    }

    private fun buildIncome(rawIncome: JSONObject): Income {
        val income = Income()
        val dateFormat = SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH)

        income.id = rawIncome.getInt("id")
        income.value = rawIncome.getDouble("value")
//        income.category = buildCategory(rawIncome.getJSONObject("category"))
//        income.currency = buildCurrency(rawIncome.getJSONObject("currency"))
        income.tags = buildTags(rawIncome.getJSONArray("tags"))
        income.incomeAt = dateFormat.parse(rawIncome.getString("incomeAt"))

        return income
    }

    private fun buildCategory(rawCategory: JSONObject): Category {
        val category = Category()
        category.id = rawCategory.getInt("id")
        category.name = rawCategory.getString("name")

        return category
    }

    private fun buildCurrency(rawCurrency: JSONObject): Currency {
        val currency = Currency()
        currency.code = rawCurrency.getString("code")
        currency.name = rawCurrency.getString("name")
        currency.symbol = rawCurrency.getString("symbol")

        return currency
    }

    private fun buildTags(rawTags: JSONArray): Collection<Tag> {
        val tags: MutableCollection<Tag> = mutableListOf()

        (0..(rawTags.length() - 1))
                .map { rawTags.getJSONObject(it) }
                .forEach { tags.add(buildTag(it)) }

        return tags
    }

    private fun buildTag(rawTag: JSONObject): Tag {
        val tag = Tag()
        tag.id = rawTag.getInt("id")
        tag.value = rawTag.getString("value")

        return tag
    }
}