package com.tsmc.tsid.mlid

import com.tsmc.tsid.mlid.mail.EmailPayload
import io.quarkus.test.junit.QuarkusTest

import org.junit.jupiter.api.Test
import javax.inject.Inject
import io.quarkus.mailer.MockMailbox
import io.restassured.RestAssured
import io.restassured.http.ContentType

@QuarkusTest
class EmailResourceTest {

    @Test
    fun testSendEmailEndpoint() {
        val emailPayload = EmailPayload(
            recipient = "recipient@example.com",
            subject = "Test Email",
            body = "This is a test email."
        )

        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(emailPayload)
            .`when`()
            .post("/email")
            .then()
            .statusCode(200)

    }
}