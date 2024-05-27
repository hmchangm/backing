package com.tsmc.tsid.mlid.mail

data class EmailPayload(
    val recipient: List<String>,
    val subject: String,
    val body: String,
    val contentType:String,
    val from: String,
)
