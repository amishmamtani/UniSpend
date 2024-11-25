package app;

import com.resend.*;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

public class SendEmail {
    public SendEmail(String to, String subject, String body) {
        Resend resend = new Resend(System.getenv("RESEND_API_KEY"));

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("UniSpend <onboarding@resend.dev>")
                .to(to)
                .subject(subject)
                .html(body)
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
