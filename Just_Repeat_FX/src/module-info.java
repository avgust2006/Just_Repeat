module Just_Repeat_FX {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
    requires java.desktop;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.antdesignicons; 
	requires org.kordamp.ikonli.fontawesome;
	requires org.kordamp.ikonli.javafx; 
	 
  	opens application to javafx.graphics, javafx.fxml;
}
