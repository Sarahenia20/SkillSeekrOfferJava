package RunDev;

import Entities.Offer;
import Entities.Skill;
import Entities.Motive;
import Entities.Type;
import Entities.Location;
import Entities.Status;
import Services.ServiceOffer;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Offer offer = new Offer();
        offer.setTitle("maven");
        offer.setDescription("This is a sample offer description.");
        offer.setAuthor("sarah.henia@esprit.tn");
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


        // Add Offer to Database
        ServiceOffer serviceOffer = new ServiceOffer();
        try {
            serviceOffer.add(offer);
            System.out.println("Offer added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding offer: " + e.getMessage());
        }
    }
}
