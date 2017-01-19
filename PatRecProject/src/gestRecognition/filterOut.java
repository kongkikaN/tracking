
package gestRecognition;

import com.googlecode.javacpp.Loader;
import static com.googlecode.javacv.cpp.opencv_core.CV_FILLED;
import static com.googlecode.javacv.cpp.opencv_core.CV_RGB;
import static com.googlecode.javacv.cpp.opencv_core.CV_WHOLE_SEQ;
import com.googlecode.javacv.cpp.opencv_core.CvContour;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCircle;
import static com.googlecode.javacv.cpp.opencv_core.cvDrawContours;
import static com.googlecode.javacv.cpp.opencv_core.cvInRangeS;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_LINK_RUNS;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_RETR_LIST;
import com.googlecode.javacv.cpp.opencv_imgproc.CvMoments;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvContourArea;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvFindContours;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvGetCentralMoment;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvGetSpatialMoment;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvMoments;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;



public class filterOut {
    public static int t;
    public static IplImage filter(IplImage img, IplImage hsvimg, IplImage binimg, CvScalar maxc, CvScalar minc,
            CvSeq contour1, CvSeq contour2, CvMemStorage storage, CvMoments moments,
            boolean b, boolean g) throws AWTException{
        
        double areaMax , areaC = 0;
        double moment10, moment01, m_area;
        int posX = 0, posY = 0;
        Robot rbt = new Robot();
        
        areaMax = 500;
        
        cvCvtColor(img, hsvimg, CV_BGR2HSV);
        cvInRangeS(hsvimg, minc, maxc, binimg);
        
        //finding contours
        //cv_retr_list gives all contours no matter the hierarchy
        //the enumerated type
        cvFindContours(binimg, storage, contour1, Loader.sizeof(CvContour.class),
                       CV_RETR_LIST, CV_LINK_RUNS, cvPoint(0, 0));
        
        
        contour2 = contour1;
        
        while (contour1 != null && !contour1.isNull()){
                areaC = cvContourArea(contour1, CV_WHOLE_SEQ, 1);

                if (areaC > areaMax){
                    areaMax = areaC;
                }
                contour1 = contour1.h_next();
            }
        
        while (contour2 != null && !contour2.isNull()){
                areaC = cvContourArea(contour2, CV_WHOLE_SEQ, 1);

                if (areaC < areaMax)
                    cvDrawContours(binimg, contour2, CV_RGB(0,0,0), CV_RGB(0,0,0),
                            0, CV_FILLED, 8, cvPoint(0, 0));
                contour2 = contour2.h_next();
            }
        
        //if image is binary then 1, else it's 0
        cvMoments(binimg, moments, 1);
            
            moment10 = cvGetSpatialMoment(moments, 1, 0);        //returns double value
            moment01 = cvGetSpatialMoment(moments, 0, 1);
            m_area = cvGetSpatialMoment(moments, 0, 0);
            
            posX = (int) (moment10/m_area);
            posY = (int) (moment01/m_area);
            
            if (b==true)
                if (posX > 0 && posY> 0){
                    rbt.mouseMove(posX * 4, posY *3);
                    cvCircle(img, cvPoint(posX, posY), 1, cvScalar(255, 0, 0, 0), 10, 0, 0);
                }
            if (g == true){
                if (posX> 0 && posY > 0){
                    rbt.mousePress(InputEvent.BUTTON1_MASK);
                    cvCircle(img, cvPoint(posX, posY), 1, cvScalar(0, 255, 0, 0), 10, 0, 0);
                    t++;
                }
                else if (t>0){
                    rbt.mouseRelease(InputEvent.BUTTON1_MASK);
                    t = 0;
                }
            }
            
            //cvCircle(img, cvPoint(posX, posY), 1, cvScalar(125, 0, 0, 0), 10, 0, 0);
            cvShowImage("original image", img);
            cvShowImage("Contour Filtered image", binimg);
  
        return binimg;
        
    }
}
