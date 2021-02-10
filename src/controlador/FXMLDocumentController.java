/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 *
 * @author alumno
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ListView<String> listView;
    private ObservableList<String> data;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private CategoryAxis categoryAxis;
    @FXML
    private PieChart pieChart;
    
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    private XYChart.Data valorBarraData(String pais,double valor){
        XYChart.Data data = new XYChart.Data(pais,valor);
        
        String valorNumerico = String.valueOf(valor);
        Label etiqueta = new Label(valorNumerico);
        StackPane node = new StackPane();
        Group grupo = new Group(etiqueta);
        
        StackPane.setAlignment(grupo, Pos.BOTTOM_CENTER);
        StackPane.setMargin(grupo, new Insets(0,0,5,0));
        
        node.getChildren().add(grupo);
        etiqueta.setRotate(-90);
        Font fuente = new Font("System",12);
        etiqueta.setFont(fuente);
        data.setNode(node);
        
        
        return data;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        data = FXCollections.observableArrayList(
            "chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown");
        listView.setItems(data);
        
        listView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> ov, 
                    String old_val, String new_val) {
                        System.out.println("Elemento seleccionado "+new_val);
            }
        });
        
        listView.setCellFactory(new Callback<ListView<String>, 
            ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> list) {
                    return new BtnLblCell();
                }
            }
        );
        
        
        //barchart
        XYChart.Series series2003 = new XYChart.Series();
        series2003.setName("2003");       
        series2003.getData().add(valorBarraData("austria", 25601.34));
        series2003.getData().add(valorBarraData("brazil", 20148.82));
        series2003.getData().add(valorBarraData("france", 10000));
        series2003.getData().add(valorBarraData("italy", 35407.15));
        series2003.getData().add(valorBarraData("usa", 12000)); 
        
        XYChart.Series series2004 = new XYChart.Series();
        series2004.setName("2004");
        series2004.getData().add(valorBarraData("austria", 57401.85));
        series2004.getData().add(valorBarraData("brazil", 41941.19));
        series2004.getData().add(valorBarraData("france", 45263.37));
        series2004.getData().add(valorBarraData("italy", 117320.16));
        series2004.getData().add(valorBarraData("usa", 14845.27));  
        
        XYChart.Series series2005 = new XYChart.Series();
        series2005.setName("2005");
        series2005.getData().add(valorBarraData("austria", 45000.65));
        series2005.getData().add(valorBarraData("brazil", 44835.76));
        series2005.getData().add(valorBarraData("france", 18722.18));
        series2005.getData().add(valorBarraData("italy", 17557.31));
        series2005.getData().add(valorBarraData("usa", 92633.68)); 
        
        this.categoryAxis.setLabel("Países");
        this.numberAxis.setLabel("Valores aleatorios");
        barChart.getData().addAll(series2003,series2004,series2005);
        
        
        //piechart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        pieChart.setData(pieChartData);
        pieChart.setTitle("Frutas importadas");
        pieChart.setLegendSide(Side.BOTTOM);
        
        
    }    

    private static class BtnLblCell extends ListCell<String> {
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            GridPane gridPane = new GridPane();
            Button btn = new Button();
            Label lbl = new Label();
            gridPane.setPrefWidth(200);
            ColumnConstraints clm1 = new ColumnConstraints();
            clm1.setPercentWidth(90);
            ColumnConstraints clm2 = new ColumnConstraints();
            clm2.setPercentWidth(30);
            clm2.setHalignment(HPos.RIGHT);
            gridPane.getColumnConstraints().addAll(clm1,clm2);
            
            lbl.setText(item);
            btn.setText("Boton");
            btn.setId(item);
            
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    System.out.println(((Button)arg0.getSource()).getId());

                }
            });     
            
            
            gridPane.add(lbl, 0, 0);
            gridPane.add(btn, 1, 0);
            
            if (item != null) {
                setGraphic(gridPane);
            }
        }
    }
    
}
