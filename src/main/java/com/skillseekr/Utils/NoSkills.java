package com.skillseekr.Utils;

import com.skillseekr.Models.Offers.Offer;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Date;

public class NoSkills {

    // Validate all fields except file: Not null
    public static String validateAllFieldsNotNull(Offer offer) {
        if (offer.getTitle() == null) {
            return "Title cannot be empty";
        } else if (!validateTitle(offer.getTitle())) {
            return "Title must be at most 100 characters long";
        } else if (!validateDescription(offer.getDescription())) {
            return "Description must be at least 150 characters long";
        } else if (!validateAuthor(offer.getAuthor())) {
            return "Invalid author email address";
        } else if (!validateCreatedAt(offer.getCreated_at())) {
            return "Creation date cannot be in the past";
        } else if (offer.getMotive() == null) {
            return "Motive cannot be empty";
        } else if (offer.getType() == null) {
            return "Type cannot be empty";
        } else if (offer.getLocation() == null) {
            return "Location cannot be empty";
        } else if (offer.getStatus() == null) {
            return "Status cannot be empty";
        } else {
            return null; // Validation passed
        }
    }

    // Validate title: Max 100 characters long
    private static boolean validateTitle(String title) {
        return title.length() <= 100;
    }

    // Validate description: At least 100 characters long
    private static boolean validateDescription(String description) {
        return description.length() >= 150;
    }

    // Validate author: Should be a valid email address
    private static boolean validateAuthor(String author) {
        try {
            InternetAddress internetAddress = new InternetAddress(author);
            internetAddress.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    // Validate createdAt: Should not be in the past
    private static boolean validateCreatedAt(Date createdAt) {
        return createdAt.compareTo(new Date()) <= 0;
    }
}
