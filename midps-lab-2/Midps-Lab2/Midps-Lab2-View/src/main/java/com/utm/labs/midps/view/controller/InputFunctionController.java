package com.utm.labs.midps.view.controller;

import com.utm.labs.businesslogic.Calculator;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author andrei
 */
public class InputFunctionController extends Application implements Initializable {

   @FXML private Button btn_draw;
   @FXML private TextField input_function; 
   
   public static Pair<Float,Float> coeficient_parameter;
   
   private static final Logger LOG = Logger.getLogger(InputFunctionController.class.getName());
   
   

   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_draw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_draw_click();
            }
        });
        
        
    }    

    public void btn_draw_click() {
         String function=input_function.getText();
        char sign=getSign(function);
        char[] p_1=new char[10];
        char[] p_2=new char[10];
        
        if(sign==Calculator.SUBTRACTION){
            function.getChars(0, function.lastIndexOf(sign), p_1, 0);
            function.getChars(function.lastIndexOf(sign),function.length() , p_2, 0);  // include sign "-" in parameter_2
        } else {
            function.getChars(0, function.lastIndexOf(sign), p_1, 0);
            function.getChars(function.lastIndexOf(sign)+1,function.length() , p_2, 0);
        }
        
        
        LOG.info("Parameter_1 = "+String.valueOf(p_1));
        LOG.info("Parameter_2 = "+String.valueOf(p_2));
        LOG.info("SIGN = "+sign);
        
        coeficient_parameter=get_coeficient_parameter(String.valueOf(p_1),String.valueOf(p_2) );
        
        LOG.info("Coeficient = "+coeficient_parameter.getKey());
        LOG.info("Parameter = "+coeficient_parameter.getValue());
       try {
           start(new Stage());
       } catch (Exception ex) {
          LOG.log(Level.SEVERE, null, ex);
       }
        
        
    }
    
   
    
    public char getSign(String function){
        if(function.contains("+")){
            return Calculator.ADDITION;
        } else if(function.contains("-")){
            return Calculator.SUBTRACTION;
        } 
        return '?';
    }
    
    public Pair<Float,Float> get_coeficient_parameter(String p1 ,String p2){
        System.out.println(p1 + "         " + p2);
        char[] coeficientChar=new char[10];
        float coeficient=0;
        float parameter=0;
        if(p1.contains("x")){
            p1.getChars(0, p1.indexOf("x"), coeficientChar, 0);
            coeficient=Float.parseFloat(String.valueOf(coeficientChar));
            parameter=Float.parseFloat(p2);
        } else {
            p2.getChars(0, p2.indexOf("x"), coeficientChar, 0);
            coeficient=Float.parseFloat(String.valueOf(coeficientChar));
            parameter=Float.parseFloat(p1);
        }
        
        return new Pair<Float,Float>(coeficient,parameter);
    }
    
  
    
    @Override
    public void start(Stage stage) throws Exception {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
       
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("MIDPS Lab_1");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        
        //populating the series with data
        
        for(int i=-10 ; i<= 10; i++){
            float f=coeficient_parameter.getKey()*i+coeficient_parameter.getValue();
            LOG.info("x = "+i+" ; "+" f(x) = "+f);
            series.getData().add(new XYChart.Data(i,f));   
        }
        
        
        Scene scene  = new Scene(lineChart,1400,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }

    
    
}
