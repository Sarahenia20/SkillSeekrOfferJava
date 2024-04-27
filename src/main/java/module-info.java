module com.skillseekr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires mail;
    requires java.sql;
    requires fontawesomefx;
    requires java.desktop;
    requires org.apache.commons.io;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.skillseekr to javafx.fxml; // Opening the com.skillseekr package
    opens com.skillseekr.Offer to javafx.fxml;
    opens com.skillseekr.Hire to javafx.fxml;
    opens com.skillseekr.Claims to javafx.fxml;
    opens com.skillseekr.Projects to javafx.fxml;
    opens com.skillseekr.User to javafx.fxml;
    // Opening the com.skillseekr.Offer package

    opens com.skillseekr.Models.User to javafx.base;
    opens com.skillseekr.Models.Offers to javafx.base;
    opens com.skillseekr.Models.Hire to javafx.base;// Opening the com.skillseekr.Models.Offers package to javafx.base

    exports com.skillseekr;
    exports com.skillseekr.Offer;
}
