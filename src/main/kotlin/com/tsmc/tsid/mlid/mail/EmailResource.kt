package com.tsmc.tsid.mlid.mail

import io.smallrye.mutiny.coroutines.awaitSuspending
import io.vertx.ext.mail.MailMessage
import io.vertx.mutiny.ext.mail.MailClient
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/email")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class EmailResource(val mailClient: MailClient) {
    @POST
    suspend fun sendEmail(payload: EmailPayload): Response {
        if(payload.body.isEmpty()){
            throw RuntimeException("Should have one of text or html content")
        }
        if(payload.recipient.isEmpty()){
            throw RuntimeException("Should have receiver")
        }
        MailMessage().apply {
            subject = payload.subject
            from = payload.from
            to = payload.recipient
            if("html"==payload.contentType.lowercase()){
                html = payload.body
            }else{
                text = payload.body
            }
        }.let { message ->
            mailClient.sendMail(message).awaitSuspending()
        }
        return Response.ok("OK").build()
    }
}
