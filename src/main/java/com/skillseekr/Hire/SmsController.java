package com.skillseekr.Hire;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;


public class SmsController {



        // Find your Account Sid and Token at twilio.com/console
        public static final String ACCOUNT_SID = "ACf199d9d68b0f08cf5fcc12db12c9e8d1";
        public static final String AUTH_TOKEN = "5abff441cd4fca20c4a3e0ae02348a1e";

        public static void Sms() {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+21626363325"), // DEMO
                    new com.twilio.type.PhoneNumber("+13144031320"),


                    "Commande effectuee a un client : nom: wassim"  +"la commande est dispo").create();

            System.out.println(message.getSid());
        }
    }

