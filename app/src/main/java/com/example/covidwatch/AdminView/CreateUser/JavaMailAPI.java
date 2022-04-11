package com.example.covidwatch.AdminView.CreateUser;

import android.content.Context;
import android.os.AsyncTask;

import androidx.constraintlayout.core.motion.utils.Utils;

import com.example.covidwatch.DateCalculation;

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
    private String email, subject, message, startDate, name;
    private int id;

    public JavaMailAPI(Context context, String email, String startDate, String name, int id) {
        this.context = context;
        this.email = email;
        this.startDate = startDate;
        this.name = name;
        this.id = id;

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

        DateCalculation dateCalculation = new DateCalculation();
        String endDate =  dateCalculation.findEndDate(startDate);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress("covidwatch3@gmail.com"));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject("Covid Watch");


            if( id == 1 ){
                mimeMessage.setText("Greetings, "+ name +"! \n" +
                        "\n" +
                        "Congratulations! You have successfully completed the monitoring period \n" +
                        "If you require further assistance, please contact your local health department at 3231417777(Monday-Friday from 8:30-5:00) or 3231417777 (during evening, weekend and holiday hours). \n" +
                        "\n" +
                        "\n" +
                        "Stay Safe And Healthy");
            }else{
                mimeMessage.setText("Greetings, "+ name +"! \n" +
                        "\n" +
                        "Please note that your monitoring end date is "+ endDate +  "\n" +
                        "\n" +
                        "Click the Link to begin a new Health Assessment. All information provided is confidential. \n" +
                        "\n" +
                        "If you require further assistance, please contact your local health department at 3231417777(Monday-Friday from 8:30-5:00) or 3231417777 (during evening, weekend and holiday hours). \n" +
                        "\n" +
                        "\n" +
                        "Survey Link : https://forms.gle/uCBgZBnDdpxZUEqq6 ");
            }



            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }
}
