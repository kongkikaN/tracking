
package gestRecognition;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;


public class CreateImages {
    
    public IplImage hsvimg, binimgG, binimgB, combImage;
    public int w = 320, h = 240;
    Main main = new Main();
    public CreateImages(){
        
        this.hsvimg = cvCreateImage(cvSize(this.w, this.h), 8, 3);
        //binary chanel = 1
        this.binimgG = cvCreateImage(cvSize(this.w, this.h), 8, 1);
        this.binimgB = cvCreateImage(cvSize(this.w, this.h), 8, 1);
        this.combImage = cvCreateImage(cvSize(w, h), 8, 1);
        
        main.setImages(this);
        
    }
    
}
