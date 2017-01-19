
package gestRecognition;

import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvOr;
import static com.googlecode.javacv.cpp.opencv_core.cvReleaseImage;
import static com.googlecode.javacv.cpp.opencv_core.cvReleaseMemStorage;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_ANY;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_PROP_FRAME_WIDTH;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateCameraCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame;
import static com.googlecode.javacv.cpp.opencv_highgui.cvReleaseCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSetCaptureProperty;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import com.googlecode.javacv.cpp.opencv_imgproc.CvMoments;
import java.awt.AWTException;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{
    
    
    
    /**
     * Initializing some variables
     */
    //initialize original image, hsvimage and binary image
    public static IplImage img, hsvimg, binimgG, binimgB, combImage;
    
    //w for width, h for height
    public static int w = 320, h = 240;
    
    //initialize range
        private static CvScalar Bminc = cvScalar(95, 150, 75, 0);
        private static CvScalar Bmaxc = cvScalar(145, 255, 255, 0);
        private static CvScalar Gminc = cvScalar(40, 50, 60, 0);
        private static CvScalar Gmaxc = cvScalar(80, 255, 255, 0);
    
    
    /**
     * main method - launches arguments
     * @param args 
     * @throws java.awt.AWTException 
     */
    public static void main (String[] args) throws AWTException{
        launch(args);
        
        CvSeq contour1 = new CvSeq();
        CvSeq contour2 = null;
        CvMemStorage storage = CvMemStorage.create();
        CvMoments moments = new CvMoments(Loader.sizeof(CvMoments.class));
        
        CvCapture camera = cvCreateCameraCapture(CV_CAP_ANY);
        cvSetCaptureProperty(camera, CV_CAP_PROP_FRAME_WIDTH, 320);
        cvSetCaptureProperty(camera, CV_CAP_PROP_FRAME_HEIGHT, 240);
        
        
        
        while (true){
            
            img = cvQueryFrame(camera);
            
            if (img == null) break;
            
            //filter(original image, hsvimg, binimgB, Scalara Bmax, Scalara Bmin, contour1-2, storage, moments, b, g);
            binimgB = filterOut.filter(img, hsvimg, binimgB, Bmaxc, Bminc, contour1, contour2, storage, moments, true, false);
            binimgG = filterOut.filter(img, hsvimg, binimgG, Gmaxc, Gminc, contour1, contour2, storage, moments, false, true);
            
            cvOr(binimgB, binimgG, combImage, null);
            
            cvShowImage("Original", img);
            char c = (char) cvWaitKey(15);
            
            if(c=='q') break;
            
            //cvWaitKey(30);
            contour1 = new CvSeq();
            
        }
        
        cvReleaseImage(hsvimg);
        cvReleaseImage(binimgB);
        cvReleaseImage(binimgG);
        cvReleaseImage(img);
        cvReleaseImage(combImage);
        cvReleaseMemStorage(storage);
        cvReleaseCapture(camera);
        
    }
    
    
    
    /**
     * abstract method Overrides start
     * @param stage
     * @throws Exception 
     * @throws java.awt.AWTException 
     */
    @Override
    public void start(Stage stage) throws Exception, AWTException {
        //create control panel
        Stage window = stage;
        window.setTitle("Control Panel");
        ControlPanel cp = new ControlPanel(window);
        //create images
        CreateImages ci = new CreateImages();
        
    }
    
    
    
    /**
     * Called when button from ControlPanel is pressed
     * to change values of color to recognize
     * 
     * @param cp 
     */
    
    public void setValues(ControlPanel cp){
        this.Bminc = cp.Bminc;
        this.Bmaxc = cp.Bmaxc;
        this.Gminc = cp.Gminc;
        this.Gmaxc = cp.Gmaxc;
        System.out.println(this.Bminc + "  " + this.Bmaxc + "  " + this.Gminc + "  " + this.Gmaxc);

    }
    
    /**
     * Initializing the images
     * Gets values from class CreateImages.java
     * @param ci 
     */
    public void setImages(CreateImages ci){
        
        this.hsvimg = ci.hsvimg;
        this.binimgG = ci.binimgG;
        this.binimgB = ci.binimgB;
        this.combImage = ci.combImage;
        
    }
}