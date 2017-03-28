package com.utm.labs.midps.view.controller;

import com.utm.labs.businesslogic.Calculator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author andrei
 */
public class CalculatorController implements Initializable {

    @FXML
    private Label input;
    @FXML
    private Button btn_clear;
    @FXML
    private Button btn_division;
    @FXML
    private Button btn_multiplication;
    @FXML
    private Button btn_addition;
    @FXML
    private Button btn_subtraction;
    @FXML
    private Button btn_parantheses;
    @FXML
    private Button btn_result;
    @FXML
    private Button btn_power;
    @FXML
    private Button btn_point;
    @FXML
    private Button btn_root;
    @FXML
    private Button btn_alternateSign;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_graph;

    @FXML
    private Button btn_9;
    @FXML
    private Button btn_8;
    @FXML
    private Button btn_7;
    @FXML
    private Button btn_6;
    @FXML
    private Button btn_5;
    @FXML
    private Button btn_4;
    @FXML
    private Button btn_3;
    @FXML
    private Button btn_2;
    @FXML
    private Button btn_1;
    @FXML
    private Button btn_0;

    float result = 0;
    private static final Logger LOG = Logger.getLogger(CalculatorController.class.getName());

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btn_1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_1_on_click();
            }
        });
        btn_2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_2_on_click();
            }
        });
        btn_3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_3_on_click();
            }
        });
        btn_4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_4_on_click();
            }
        });
        btn_5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_5_on_click();
            }
        });
        btn_6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_6_on_click();
            }
        });
        btn_7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_7_on_click();
            }
        });
        btn_8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_8_on_click();
            }
        });
        btn_9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_9_on_click();
            }
        });
        btn_0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_0_on_click();
            }
        });

        // FUNCTIONAL BUTTONS
        btn_addition.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_addition_on_click();
            }
        });

        btn_alternateSign.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_alternateSign_on_click();
            }
        });

        btn_clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_clear_on_click();
            }
        });

        btn_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_delete_on_click();
            }
        });

        btn_division.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_division_on_click();
            }
        });

        btn_multiplication.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_multiplication_on_click();
            }
        });

        btn_parantheses.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_parantheses_on_click();
            }
        });

        btn_point.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_point_on_click();
            }
        });

        btn_power.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_power_on_click();
            }
        });

        btn_result.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_result_on_click();
            }
        });

        btn_root.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_root_on_click();
            }
        });
        btn_subtraction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                btn_subtraction_on_click();
            }
        });
        btn_graph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    btn_graph_on_click();
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void btn_1_on_click() {
        if (input.getText().length() == 0) {
            input.setText("1");
        } else {
            input.setText(input.getText() + "1");
        }
    }

    private void setGetText(Label input, String str) {
        if (input.getText().length() == 0) {
            input.setText(str);
        } else {
            input.setText(input.getText() + str);
        }
    }

    private void btn_9_on_click() {
        setGetText(input, "9");
    }

    private void btn_2_on_click() {
        setGetText(input, "2");
    }

    private void btn_3_on_click() {
        setGetText(input, "3");
    }

    private void btn_4_on_click() {
        setGetText(input, "4");
    }

    private void btn_5_on_click() {
        setGetText(input, "5");
    }

    private void btn_6_on_click() {
        setGetText(input, "6");
    }

    private void btn_7_on_click() {
        setGetText(input, "7");
    }

    private void btn_8_on_click() {
        setGetText(input, "8");
    }

    private void btn_0_on_click() {
        if (input.getText().length() > 0) {
            input.setText(input.getText() + "0");
        }
    }

    private void btn_addition_on_click() {
        if (input.getText().length() > 0) {
            checkPreviousResult();
            input.setText(input.getText() + "+");
        }
    }

    private void btn_alternateSign_on_click() {
//        if(input.getText().length()>0 && input.getText().contains("=")==false){
//            StringBuilder sb=new StringBuilder(input.getText());
//            int signPos=Calculator.processInputLine(sb);
////            LOG.info("Sign pos = "+signPos);
////            LOG.info("SIGN = "+sb.charAt(signPos));
//            if(signPos==-1){
//                input.setText("-"+input.getText());
//            } else {
//                if(sb.charAt(signPos)==ADDITION){
//                    System.out.println(sb);
//                   sb.deleteCharAt(signPos);
//                   sb.setCharAt(signPos, SUBTRACTION);
//                   input.setText(sb.toString());
//                }else {
//                   sb.deleteCharAt(signPos);
//                   sb.setCharAt(signPos, ADDITION);
//                   input.setText(sb.toString());
//                }
//                
//            }
//                
//        }

    }

    private void btn_clear_on_click() {
        if (input.getText().length() > 0) {
            input.setText(" ");
            input.setTextFill(Paint.valueOf("black"));
        }
    }

    private void btn_delete_on_click() {
        if (input.getText().length() > 1) {
            StringBuilder line = new StringBuilder(input.getText());
            line.deleteCharAt(line.length() - 1);
            input.setText(line.toString());
        } else {
            input.setText(" ");
        }
    }

    private void btn_division_on_click() {
        if (input.getText().length() > 0) {
            checkPreviousResult();
            input.setText(input.getText() + "/");
        }
    }

    private void btn_multiplication_on_click() {
        if (input.getText().length() > 0) {
            checkPreviousResult();
            input.setText(input.getText() + "*");
        }
    }

    private void btn_parantheses_on_click() {
    }

    private void btn_point_on_click() {
        if (input.getText().length() > 0) {
            input.setText(input.getText() + ".");
        }
    }

    private void btn_power_on_click() {
        if (input.getText().length() > 0) {
            input.setText(input.getText() + "^");
        }

    }

    private void btn_result_on_click() {

        if (isSqrtOperation() == true) {
            StringBuilder sb = new StringBuilder(input.getText());
            sb.deleteCharAt(0);
            float parameter = Float.parseFloat(sb.toString());
            if (parameter > 0) {
                input.setText(input.getText() + " = " + Math.sqrt(parameter));
            } else {
                input.setText("INVALID OPERATION");
                input.setTextFill(Paint.valueOf("red"));
            }

        } else if (input.getText().length() > 2) {
            if (input.getText().toString().contains("=")) {
                return;
            } else {
                StringBuilder line = new StringBuilder(input.getText());
                char sign = Calculator.getOperation(line);
                ArrayList<String> parameters = Calculator.getParameters(line, String.valueOf(sign));

                float result = Calculator.compute(parameters.get(0), parameters.get(1), sign);
                if (result / (int) result == 1) {
                    int intRes = (int) result;
                    input.setText(input.getText() + " = " + intRes);
                } else {
                    input.setText(input.getText() + " = " + result);
                }
            }
        }
    }

    private void btn_root_on_click() {
        if (input.getText().length() > 0) {
            checkPreviousResult();
            input.setText("?" + input.getText());
        }
    }

    private void btn_subtraction_on_click() {
        if (input.getText().length() > 0) {
            checkPreviousResult();
            input.setText(input.getText() + "-");
        }
    }

    public float getResult() {
        int pos = input.getText().indexOf("=");
        char[] resultArray = new char[20];
        input.getText().getChars(pos + 1, input.getText().length(), resultArray, 0);

        return Float.parseFloat(String.valueOf(resultArray));
    }

    public void checkPreviousResult() {
        if (input.getText().toString().contains("=")) {
            result = getResult();
            int intResult = (int) result;
            if (result / intResult == 1) {
                input.setText(String.valueOf(intResult));
            } else {
                input.setText(String.valueOf(result));
            }
        }
    }

    private void btn_graph_on_click() throws IOException {
        //((Stage) button_LogIn.getScene().getWindow()).close();
        URL url = new File("src/main/java/com/utm/labs/midps/view/fxml/FXMLInputFunction.fxml").toURL();
        Parent root = FXMLLoader.load(url);
//        Parent root=FXMLLoader.load(getClass().getResource("/calculator/FXML/FXMLInputFunction.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    private boolean isSqrtOperation() {

        if (input.getText().charAt(0) == '?') {
            return true;
        }

        return false;
    }

}
