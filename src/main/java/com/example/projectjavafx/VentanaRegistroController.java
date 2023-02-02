package com.example.projectjavafx;

import java.sql.SQLException;

public class VentanaRegistroController {
    public VentanaRegistroController() {
        LoginController loginController = new LoginController();
        VentanaRegistro ventana = new VentanaRegistro();

        ventana.getRegisterBtn().setOnAction(e -> {
            try {
                if (loginController.handleRegistration(ventana.getUser().getText(), ventana.getPass().getText())) {
                    ventana.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        ventana.getCloseBtn().setOnAction(e -> ventana.close());

        ventana.show();
    }
}
