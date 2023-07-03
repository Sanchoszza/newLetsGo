package com.android.letsgo.view

import com.android.letsgo.db.PersonData

interface PersonDataView {
    fun message(str: String)

    fun getData(data: PersonData)
}