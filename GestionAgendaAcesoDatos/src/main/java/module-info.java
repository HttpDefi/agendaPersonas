module IMF.GestionAgendaAcesoDatos {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.web;
	requires java.desktop;

    opens IMF.GestionAgendaAcesoDatos to javafx.fxml, javafx.base;
    opens Modelos to javafx.fxml, javafx.base;
    exports IMF.GestionAgendaAcesoDatos;
}
