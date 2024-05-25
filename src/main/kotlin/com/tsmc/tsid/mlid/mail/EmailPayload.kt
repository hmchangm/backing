package com.tsmc.tsid.mlid.mail

data class EmailPayload(
    val recipient: String,
    val subject: String,
    val body: String,
    val type: String = "text",
)