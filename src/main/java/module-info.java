module com.userinterface {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.textstatistics to javafx.fxml;
    exports com.textstatistics;
}