module com.carlosserrano.basicojfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;
    requires java.base;

    opens com.carlosserrano.basicojfx.model to java.xml.bind;
    opens com.carlosserrano.basicojfx.controller to javafx.fxml;
    exports com.carlosserrano.basicojfx;
}