module com.mycompany.flight {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.flight to javafx.fxml;
    exports com.mycompany.flight;
    exports com.mycompany.pojo;
}
