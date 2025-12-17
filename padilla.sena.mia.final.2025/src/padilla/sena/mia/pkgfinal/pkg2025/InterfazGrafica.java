package padilla.sena.mia.pkgfinal.pkg2025;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.List;

public class InterfazGrafica extends Application {

    private GestorInventario<Producto> miTienda = new GestorInventario<>();
    
//  Lista para que la tabla se actualice sola
    private ObservableList<Producto> listaObservable = FXCollections.observableArrayList();
    
//  Componentes GUI 
    private TableView<Producto> tabla;
    private TextField txtNombre, txtMarca, txtPrecio;
    private ComboBox<Condicion> cmbCondicion;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Gestión - Final Programación II");    
        
//      Configuraciones de la tabla 
        tabla = new TableView<>();
        
        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        TableColumn<Producto, String> colMarca = new TableColumn<>("Marca");
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<Producto, String> colCondicion = new TableColumn<>("Condición");
        colCondicion.setCellValueFactory(new PropertyValueFactory<>("condicion"));
        
        tabla.getColumns().addAll(colMarca, colNombre, colPrecio, colCondicion);
        tabla.setItems(listaObservable); // Vincula la lista visual con la tabla
        
//       Panel de control
        Button btnGuardar = new Button("Guardar (.dat)");
        btnGuardar.setOnAction(e -> guardarDatos());
        
        Button btnCargar = new Button("Cargar (.dat)");
        btnCargar.setOnAction(e -> cargarDatos());
        
        Button btnExportar = new Button("Exportar Reporte (.txt)");
        btnExportar.setOnAction(e -> exportarReporte());
        
        Button btnOrdenarPrecio = new Button("Ordenar x Precio");
        btnOrdenarPrecio.setOnAction(e -> {
            miTienda.ordenar(new ComparadorPrecio());
            actualizarTabla();
        }); 
        
        Button btnOferta = new Button("Aplicar 10% Descuento");
        btnOferta.setOnAction(e -> {
            miTienda.aplicarAccion(p -> p.aplicarDescuentos(10));
            actualizarTabla();
            mostrarAlerta("Éxito", "Descuento aplicado a todo el inventario.");
        });
        
        HBox topMenu = new HBox(10, btnGuardar, btnCargar, btnExportar, btnOrdenarPrecio, btnOferta);
        topMenu.setPadding(new Insets(10));
        
//      Formulario para ingresar 
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        txtMarca = new TextField();
        txtMarca.setPromptText("Marca");
        txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio");
        
        cmbCondicion = new ComboBox<>();
        cmbCondicion.getItems().addAll(Condicion.values());
        cmbCondicion.setValue(Condicion.NUEVO);
        
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> agregarProducto());
        
        Button btnEliminar = new Button("Eliminar Seleccionado");
        btnEliminar.setOnAction(e -> eliminarProducto());

        HBox formulario = new HBox(10, txtMarca, txtNombre, txtPrecio, cmbCondicion, btnAgregar, btnEliminar);
        formulario.setPadding(new Insets(10));
        
//      Layout 
        BorderPane layout = new BorderPane();
        layout.setTop(topMenu);
        layout.setCenter(tabla);
        layout.setBottom(formulario);

        Scene scene = new Scene(layout, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//  MÉTODOS AUXILIARES

    private void agregarProducto() {
        try {
            String nombre = txtNombre.getText(); 
            String marca = txtMarca.getText(); 
            double precio = Double.parseDouble(txtPrecio.getText()); 
            Condicion cond = cmbCondicion.getValue(); 
            
            Producto nuevo = new Celular(4000, 12, nombre, precio, marca, cond); 
            
            miTienda.agregar(nuevo);
            actualizarTabla();
            limpiarFormulario();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio debe ser un número válido.");
        }
    }

    private void eliminarProducto() {
        Producto seleccionado = tabla.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                miTienda.eliminar(seleccionado); 
                actualizarTabla();
            } catch (ProductoNoEncontradoExcepcion ex) { // CORRECCIÓN 2: Exception (inglés)
                mostrarAlerta("Error", ex.getMessage());
            }
        } else {
            mostrarAlerta("Atención", "Selecciona un producto de la tabla.");
        }
    }

    private void guardarDatos() {
        GestorArchivos.guardarBinario("datos_gui.dat", miTienda.listar());
        mostrarAlerta("Sistema", "Datos guardados correctamente.");
    }

    private void cargarDatos() {
        List<Producto> datosCargados = GestorArchivos.cargarBinario("datos_gui.dat");
        
        listaObservable.clear();
        listaObservable.addAll(datosCargados);
        
        miTienda = new GestorInventario<>();
        for(Producto p : datosCargados) {
            miTienda.agregar(p);
        }
        actualizarTabla();
    }
    
    private void exportarReporte() {
        GestorArchivos.exportarReporte("reporte_gui.txt", miTienda.listar());
        mostrarAlerta("Sistema", "Reporte exportado a reporte_gui.txt");
    }

    private void actualizarTabla() {
        listaObservable.setAll(miTienda.listar());
        tabla.refresh();
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtMarca.clear();
        txtPrecio.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}