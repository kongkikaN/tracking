
package gestRecognition;

import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ControlPanel {
    //init min and max rgb value for blue and green
    public CvScalar Bminc;
    public CvScalar Bmaxc;
    public CvScalar Gminc;
    public CvScalar Gmaxc;
    
    public CvScalar[] arr;
    
    public static int minValue = 0;
    public static int maxValue = 255;
    
    public Slider BmincRED = new Slider(this.minValue, this.maxValue, 95);
    public Slider BmincGREEN = new Slider(this.minValue, this.maxValue, 150);
    public Slider BmincBLUE = new Slider(this.minValue, this.maxValue, 75);
    public Slider BmaxcRED = new Slider(this.minValue, this.maxValue, 145);
    public Slider BmaxcGREEN = new Slider(this.minValue, this.maxValue, 255);
    public Slider BmaxcBLUE = new Slider(this.minValue, this.maxValue, 255);
    public Slider GmincRED = new Slider(this.minValue, this.maxValue, 40);
    public Slider GmincGREEN = new Slider(this.minValue, this.maxValue, 50);
    public Slider GmincBLUE = new Slider(this.minValue, this.maxValue, 60);
    public Slider GmaxcRED = new Slider(this.minValue, this.maxValue, 80);
    public Slider GmaxcGREEN = new Slider(this.minValue, this.maxValue, 255);
    public Slider GmaxcBLUE = new Slider(this.minValue, this.maxValue, 255);
    
    
    public ControlPanel(Stage window){
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        
        //creating components
        /**
         * 
         * 12 sliders -> BmincRED, BmincGREEN, BmincBLUE
         *               BmaxcRED, BmaxcGREEN, BmaxcBLUE
         *               GmincRED, GmincGREEN, GmincBLUE
         *               GmaxcRED, GmaxcGREEN, GmaxcBLUE
         * 
         * 20 labels  -> BmincREDL, BmincGREENL etc
         * 
         * 1 Button   -> confirm
         * 
         */
        
        
        Button button = new Button("Submit");
        
        Label blue = new Label("Blue");
        Label green = new Label("Green");
        Label color1 = new Label("color");
        Label color2 = new Label("color");
        Label min1 = new Label("min");
        Label max1 = new Label("max");
        Label min2 = new Label("min");
        Label max2 = new Label("max");
        Label val1 = new Label("Value");
        Label val2 = new Label("Value");
        Label val3 = new Label("Value");
        Label val4 = new Label("Value");
        Label r1 = new Label("R");
        Label g1 = new Label("G");
        Label b1 = new Label("B");
        Label r2 = new Label("R");
        Label g2 = new Label("G");
        Label b2 = new Label("B");
        Label r3 = new Label("R");
        Label g3 = new Label("G");
        Label b3 = new Label("B");
        Label r4 = new Label("R");
        Label g4 = new Label("G");
        Label b4 = new Label("B");
        
        
        //child column row
        GridPane.setConstraints(blue, 0, 0);
        GridPane.setConstraints(color1, 1, 0);
        GridPane.setConstraints(green, 2, 0);
        GridPane.setConstraints(color2, 3, 0);
        GridPane.setConstraints(min1, 0, 1);
        GridPane.setConstraints(val1, 1, 1);
        GridPane.setConstraints(min2, 2, 1);
        GridPane.setConstraints(val2, 3, 1);
        GridPane.setConstraints(r1, 0, 2);
        GridPane.setConstraints(this.BmincRED, 1, 2);
        GridPane.setConstraints(r2, 2, 2);
        GridPane.setConstraints(this.GmincRED, 3, 2);
        GridPane.setConstraints(g1, 0, 3);
        GridPane.setConstraints(this.BmincGREEN, 1, 3);
        GridPane.setConstraints(g2, 2, 3);
        GridPane.setConstraints(this.GmincGREEN, 3, 3);
        GridPane.setConstraints(b1, 0, 4);
        GridPane.setConstraints(this.BmincBLUE, 1, 4);
        GridPane.setConstraints(b2, 2, 4);
        GridPane.setConstraints(this.GmincBLUE, 3, 4);
        GridPane.setConstraints(max1, 0, 5);
        GridPane.setConstraints(val3, 1, 5);
        GridPane.setConstraints(max2, 2, 5);
        GridPane.setConstraints(val4, 3, 5);
        GridPane.setConstraints(r3, 0, 6);
        GridPane.setConstraints(this.BmaxcRED, 1, 6);
        GridPane.setConstraints(r4, 2, 6);
        GridPane.setConstraints(this.GmaxcRED, 3, 6);
        GridPane.setConstraints(g3, 0, 7);
        GridPane.setConstraints(this.BmaxcGREEN, 1, 7);
        GridPane.setConstraints(g4, 2, 7);
        GridPane.setConstraints(this.GmaxcGREEN, 3, 7);
        GridPane.setConstraints(b3, 0, 8);
        GridPane.setConstraints(this.BmaxcBLUE, 1, 8);
        GridPane.setConstraints(b4, 2, 8);
        GridPane.setConstraints(this.GmaxcBLUE, 3, 8);
        GridPane.setConstraints(button, 0, 9);
        
        grid.getChildren().addAll(r1, r2, r3, r4, g1, g2, g3, g4, b1, b2, b3, b4,
        blue, green, color1, color2, min1, min2, max1, max2,this.BmincRED,this.BmincGREEN, this.BmincBLUE,
        this.GmincRED, this.GmincGREEN, this.GmincBLUE, this.BmaxcRED, this.BmaxcGREEN, this.BmaxcBLUE,
        this.GmaxcRED, this.GmaxcGREEN, this.GmaxcBLUE, button);
        
        Scene scene = new Scene(grid);
        
        Main main = new Main();
        
        button.setOnAction(e -> {
        
            this.Bminc = cvScalar(this.BmincRED.getValue(), this.BmincGREEN.getValue(), this.BmincBLUE.getValue(), 0);
            this.Bmaxc = cvScalar(this.BmaxcRED.getValue(), this.BmaxcGREEN.getValue(), this.BmaxcBLUE.getValue(), 0);
            this.Gminc = cvScalar(this.GmincRED.getValue(), this.GmincGREEN.getValue(), this.GmincBLUE.getValue(), 0);
            this.Gmaxc = cvScalar(this.GmaxcRED.getValue(), this.GmaxcGREEN.getValue(), this.GmaxcBLUE.getValue(), 0);
            
            main.setValues(this);
            
            
        });
        
        
        window.setScene(scene);
        window.show();
        
        
        System.out.println(this.Bminc);
       
    }
    
    
}
