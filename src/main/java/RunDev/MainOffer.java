package RunDev;

import Entities.Offers.Offer;
import Entities.Offers.Skill;
import Entities.Offers.Motive;
import Entities.Offers.Type;
import Entities.Offers.Location;
import Entities.Offers.Status;
import Services.Offers.ServiceOffer;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.OfferInputValidation;

public class MainOffer {
    public static void main(String[] args) {
        Offer offer = new Offer();
        offer.setTitle("Package1");
        offer.setDescription("Explore the depths of a mysterious forest filled with secrets and magic of JAVA such as maven dependancy and shit becuase jadore le java Pi");
        offer.setAuthor("sarah_henia");
        offer.setCreated_at(new Date());
        offer.setMotive(Motive.Beta);
        offer.setType(Type.Mission);
        offer.setLocation(Location.Hybrid);
        offer.setStatus(Status.Published);
        offer.setFile_name("sample_file.txt");
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Java"));
        skills.add(new Skill("JavaFx"));
        offer.setSkills(skills);

        // Validate offer fields
        String validationResult = OfferInputValidation.validateAllFieldsNotNull(offer);
        if (validationResult != null) {
            // If validation failed, print the error message
            System.out.println("Error: " + validationResult);
        } else {
            // If validation passed, add the offer to the database
            ServiceOffer serviceOffer = new ServiceOffer();
            try {
                serviceOffer.add(offer);
                System.out.println("Offer added successfully.");
            } catch (SQLException e) {
                System.out.println("Error adding offer: " + e.getMessage());
            }
        }
    }
}