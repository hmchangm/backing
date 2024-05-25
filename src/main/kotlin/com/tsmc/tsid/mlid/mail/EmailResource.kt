package com.tsmc.tsid.mlid.mail

import io.quarkus.mailer.Mail
import io.quarkus.mailer.reactive.ReactiveMailer
import io.smallrye.mutiny.coroutines.awaitSuspending
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/email")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class EmailResource(val mailer: ReactiveMailer) {

    @POST
    suspend fun sendEmail(emailPayload: EmailPayload): Response =
        when (emailPayload.type) {
            "html" -> mailer.send(
                Mail.withHtml(emailPayload.recipient, emailPayload.subject, emailPayload.body)
            ).awaitSuspending()
            else -> mailer.send(
                Mail.withText(emailPayload.recipient, emailPayload.subject, emailPayload.body)
            ).awaitSuspending()
        }.run { Response.ok().build() }

}