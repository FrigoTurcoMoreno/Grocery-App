package com.mftjc.spesa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var quantity: String = ""

)
