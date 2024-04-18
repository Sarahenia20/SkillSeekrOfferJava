package RunDev;

import Entities.Recruitement.Recrutement;
import Services.Recruitement.ServiceRecrutement;
import Utils.RecrutementInputValidation;
import java.util.Date;
import java.sql.SQLException;

public class MainRecrutement {
    public static void main(String[] args) {
        Recrutement recrutement = new Recrutement();
        recrutement.setDate(new Date());
        recrutement.setTitre("Controllers/Recrutement");
        recrutement.setDescription("Approved");
        recrutement.setStatut("Approved");

        // Validate the Recrutement object
        if (!RecrutementInputValidation.validateRecrutement(recrutement)) {
            System.out.println("Invalid Recrutement object. Aborting operation.");
            return;
        }

        ServiceRecrutement serviceRecrutement = new ServiceRecrutement();
        try {
            serviceRecrutement.add(recrutement);
            System.out.println("Recrutement added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding Recrutement: " + e.getMessage());
        }
    }
}
