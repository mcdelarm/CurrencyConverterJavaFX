module com.example.currencyconverterjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.currencyconverterjavafx to javafx.fxml;
    exports com.example.currencyconverterjavafx;
}