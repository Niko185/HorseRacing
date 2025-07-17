package com.example.horseracing.data.local.converter

import androidx.room.TypeConverter
import com.example.horseracing.domain.model.Horse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HorseListConverter {
    @TypeConverter
    fun fromHorseList(horseList: List<Horse>): String {
        return Gson().toJson(horseList)
    }

    @TypeConverter
    fun toHorseList(horseListString: String): List<Horse> {
        val type = object : TypeToken<List<Horse>>() {}.type
        return Gson().fromJson(horseListString, type)
    }
}