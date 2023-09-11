package com.socialmarkaz.app.Models

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SearchHistoryDialogFragment : DialogFragment() {
    private  val SEARCH_HISTORY_KEY = "search_history"

    private lateinit var listener: SearchHistoryDialogListener

    interface SearchHistoryDialogListener {
        fun onSearchHistoryItemClick(query: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as SearchHistoryDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement SearchHistoryDialogListener")
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val searchHistory = getSearchHistory(requireContext())

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Search History")
        builder.setItems(searchHistory.toTypedArray()) { _, which ->
            val query = searchHistory.elementAt(which)
            listener.onSearchHistoryItemClick(query)
        }

        return builder.create()
    }
    fun getSearchHistory(context: Context): Set<String> {
        val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet(SEARCH_HISTORY_KEY, emptySet()) ?: emptySet()
    }

}
