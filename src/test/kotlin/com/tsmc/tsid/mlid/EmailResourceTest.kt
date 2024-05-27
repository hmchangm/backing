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
            recipient = listOf("recipient@example.com"),
            subject = "Test Email",
            body = "This is a test email.",
            contentType = "text",
            from = "brandy@txmx.com"
        )

        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(emailPayload)
            .`when`()
            .post("/email")
            .then()
            .statusCode(200)

        EmailPayload(
            recipient = listOf("recipient@example.com","abad@ggga.dcd"),
            subject = "Test Email 2 ",
            body = "This is a test email.",
            contentType = "html",
            from = "brandy@txmx.com"
        ).let {
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(it)
                .`when`()
                .post("/email")
                .then()
                .statusCode(200)
        }
    }
}