package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import io.quarkus.mailer.reactive.ReactiveMailer;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Alois Roscher
 * **/

@Path("/mail")
public class MailService {

    @Inject
    Mailer mailer;

    @GET
    @Blocking
    @Path("/{email}")
    public void sendEmail(@PathParam String email) {
        mailer.send(
                Mail.withText(email,
                        "Bestellbestaetigung",
                        "Ihre Bestellung ist erfolgreich bei uns eingegangen. Vielen Dank!"
                )
        );
    }
}

