package com.octo.project.history

import com.octo.project.History
import com.octo.project.HistoryQueries
import com.octo.project.SqlDelightDatabase
import com.octo.project.multi.sqlDriver

class HistoryRepository {

    /**
     * Gets history queries
     */
    private fun getHistoryQueries(): HistoryQueries? {
        sqlDriver?.let { driver ->
            val database = SqlDelightDatabase(driver)
            return database.historyQueries
        }
        return null
    }

    /**
     * Get history rates from the database
     */
    fun getHistory(): MutableList<History>? {
        val historyQueries = getHistoryQueries()
        historyQueries?.let {
            return it.selectAll().executeAsList().toMutableList()
        }
        return arrayListOf()
    }

    /**
     * add rate to the database
     */
    fun addRate(
        origin: String,
        originValue: String,
        destination: String,
        destinationValue: String,
        date: String
    ) {
        val historyQueries = getHistoryQueries()
        historyQueries?.insertRow(origin, originValue, destination, destinationValue, date)
    }
}