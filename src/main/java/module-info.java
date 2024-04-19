module com.skillseekr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires mail;
    requires java.sql;
    requires fontawesomefx;

    opens com.skillseekr to javafx.fxml; // Opening the com.skillseekr package
    opens com.skillseekr.Offer to javafx.fxml;
    opens com.skillseekr.Hire to javafx.fxml;// Opening the com.skillseekr.Offer package

    opens Models.Offers to javafx.base;
    opens Models.Hire to javafx.base;// Opening the Models.Offers package to javafx.base

    exports com.skillseekr;
}
