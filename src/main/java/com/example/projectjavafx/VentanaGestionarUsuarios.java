package com.example.projectjavafx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import java.sql.SQLException;

public class VentanaGestionarUsuarios extends VentanaBase {
    private TableView<Usuario> tablaUsuarios;
    private VentanaGestionarUsuariosController controller;

    public VentanaGestionarUsuarios() throws SQLException {
        super("Gestionar Usuarios", Modality.WINDOW_MODAL);

        controller = new VentanaGestionarUsuariosController();

        tablaUsuarios = new TableView<>();
        TableColumn<Usuario, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("username"));
        tablaUsuarios.getColumns().add(colNombre);

        TableColumn<Usuario, Button> colBoton = new TableColumn<>("Delete");

        // Asignar los datos de los usuarios a la tabla
        tablaUsuarios.getColumns().add(colBoton);
        tablaUsuarios.setItems(controller.getUsuarios());

        // Asignar los botones a la tabla
        colBoton.setCellFactory(param -> new TableCell<Usuario, Button>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    Usuario usuario = getTableView().getItems().get(getIndex());
                    try {
                        controller.deleteUsuario(usuario);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        });

        // Agregar la tabla a la escena y mostrar la ventana
        Scene scene = new Scene(tablaUsuarios, 400, 300);
        window.setScene(scene);
        window.show();
    }

}
