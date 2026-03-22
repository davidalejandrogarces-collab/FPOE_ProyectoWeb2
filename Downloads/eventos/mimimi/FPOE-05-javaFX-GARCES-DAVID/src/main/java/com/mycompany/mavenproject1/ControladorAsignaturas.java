package com.mycompany.mavenproject1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author David Garces
 */
public class ControladorAsignaturas {

    private Logica logica = new Logica();
    @FXML
    private TextField txfCodigo;
    @FXML
    private TextField txfNombre;
    @FXML
    private TextField txfCreditos;
    @FXML
    private TextField txfIntensidad;

    @FXML
    private void cancelar() {

        txfCodigo.setText("");
        txfNombre.setText("");
        txfCreditos.setText("");
        txfIntensidad.setText("");

        System.out.println("Formulario limpiado");
    }

    @FXML
    private void buscar() {

        Asignatura a = logica.buscarAsignatura(txfCodigo.getText());

        if (a != null) {

            txfNombre.setText(a.getNombre());
            txfCreditos.setText(String.valueOf(a.getCreditos()));
            txfIntensidad.setText(String.valueOf(a.getIntensidad()));
        } else {

            System.out.println("No se encontro la asignatura");
        }
    }

    @FXML
    public void guardar() {

        try {

            if (txfCodigo.getText().isBlank() || txfNombre.getText().isBlank()) {
                throw new IllegalArgumentException("El código y el nombre no pueden estar vacíos.");
            }

            if (txfCreditos.getText().isBlank() || txfIntensidad.getText().isBlank()) {
                throw new NumberFormatException();
            }

            byte creditos = Byte.parseByte(txfCreditos.getText());
            byte intensidad = Byte.parseByte(txfIntensidad.getText());

            if (creditos < 0 || intensidad < 0) {
                throw new IllegalArgumentException("Los créditos y la intensidad no pueden ser negativos.");
            }

            Asignatura asignatura = new Asignatura(
                    txfCodigo.getText(),
                    txfNombre.getText(),
                    creditos,
                    intensidad
            );
            this.logica.guardarAsignatura(asignatura);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Gestion Asignaturs");
            alert.setHeaderText("Asignatura guardada");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("gestionar Asignatura");
            alert.setHeaderText("Asignatura no guardada ");
            alert.setContentText("Debe ingresar los creditos y la intencidad (solo números positivos)");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Gestionar Asignaturas");
            alert.setHeaderText("Asignatura no guardada ");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Gestionar Asignaturas");
            alert.setHeaderText(" se genero un error desconocido  ");
            alert.setContentText("contacte al proveedor del programa para resolver el error");
            System.out.println(e);
            alert.showAndWait();
        }
    }
}
