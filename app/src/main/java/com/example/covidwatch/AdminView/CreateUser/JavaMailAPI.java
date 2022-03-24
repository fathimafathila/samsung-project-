package com.example.covidwatch.AdminView.CreateUser;

import android.content.Context;
import android.os.AsyncTask;

import androidx.constraintlayout.core.motion.utils.Utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void, Void, Void> {

    private Context context;

    private Session session;
    private String email, subject, message;

    public JavaMailAPI(Context context, String email) {
        this.context = context;
        this.email = email;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("covidwatch3@gmail.com", "1234covid@");
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress("covidwatch3@gmail.com"));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject("Covid Watch");
            mimeMessage.setText("Covid Watch Account Has been Crerated");
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }
}
