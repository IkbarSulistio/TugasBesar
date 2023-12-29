module com.example.tugasbesar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tugasbesar.Kasir to javafx.fxml;
    exports com.example.tugasbesar.Kasir;
//    exports com.example.tugasbesar;
//    opens com.example.tugasbesar.Kasir to javafx.fxml;
}