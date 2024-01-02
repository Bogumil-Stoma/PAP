module org.openjfx {
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	opens org.openjfx;
	exports org.openjfx;
	exports org.openjfx.controller;
	exports org.openjfx.database;
	opens org.openjfx.controller;
	opens org.openjfx.database to javafx.base;
}
