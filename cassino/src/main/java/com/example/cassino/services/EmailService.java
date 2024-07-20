package com.example.cassino.services;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${mailjet.api.key}")
    private String mailjetApiKey;

    @Value("${mailjet.api.secret}")
    private String mailjetApiSecret;

    public void sendEmail(String to, String subject, String body) throws MailjetException {
        // Criação do cliente Mailjet com as opções apropriadas
        ClientOptions options = ClientOptions.builder()
                .apiKey(mailjetApiKey)
                .apiSecretKey(mailjetApiSecret)
                .build();
        MailjetClient client = new MailjetClient(options);

        // Configuração do pedido de e-mail
        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "your-email@example.com")
                                        .put("Name", "Your Service"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", to)
                                                .put("Name", "Recipient")))
                                .put(Emailv31.Message.SUBJECT, subject)
                                .put(Emailv31.Message.TEXTPART, body)
                                .put(Emailv31.Message.HTMLPART, "<p>" + body + "</p>")
                                .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));

        try {
            // Envio do pedido de e-mail
            MailjetResponse response = client.post(request);
            if (response.getStatus() != 200) {
                throw new MailjetException("Failed to send email: " + response.getData());
            }
        } catch (MailjetException e) {
            // Propagação da exceção para ser tratada pelo chamador
            throw e;
        }
    }
}
