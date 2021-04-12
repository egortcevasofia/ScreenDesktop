import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {
    private BufferedImage image;
    private DbxClientV2 client;

    public MyThread (BufferedImage image, DbxClientV2 client) {
        this.image = image;
        this.client = client;
    }

    @Override
    public void run() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date now = new Date();
        try {

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", os);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
            InputStream is = new ByteArrayInputStream(os.toByteArray());

            InputStream in = new BufferedInputStream(is);

            client.files().uploadBuilder("/" + formatter.format(now) + ".png")
                    .uploadAndFinish(in);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
