package CrudTest;

import Models.Hire.Entretient;
import Services.Hire.ServiceEntretient;
import Utils.EntretientInputValidation;
import java.util.Date;
import java.sql.SQLException;

public class MainEntretient {
    public static void main(String[] args) {
        Entretient entretient = new Entretient();
        entretient.setDate(new Date());
        entretient.setType("");
        entretient.setResultat("Approved");
        entretient.setId_rec_id(1);

        // Validate the Entretient object
        if (!EntretientInputValidation.validateEntretient(entretient)) {
            System.out.println("Invalid Entretient object. Aborting operation.");
            return;
        }

        ServiceEntretient serviceEntretient = new ServiceEntretient();
        try {
            serviceEntretient.add(entretient);
            System.out.println("Entretient added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding entretient: " + e.getMessage());
        }
    }
}
