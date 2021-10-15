import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
Программа делает скриншот экрана через определенное время в секундах, указанное в переменной TIMER и отправляет скриншот в дропбокс.
Чтобы использовать программу, необходимо вставить сгенерированный ключ приложения в дропбоксе в строковую переменную ACCESS_TOKEN
Программа использует сторонние библиотеки https://github.com/dropbox/dropbox-sdk-java
 */
public class Main {
    final static int TIMER = 5;

    public static void main(String[] args) {
        String ACCESS_TOKEN = "";  //Вставить сгенерированный ключ приложения(токен)
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        System.out.println("Через "+ TIMER + " секунд будет сделан скриншот экрана");
        for (int i = 0;i<TIMER;i++) {
            System.out.println((i + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        //for (;;) {
            BufferedImage image = null;
            try {
                image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            } catch (AWTException e) {
                e.printStackTrace();
            }
            ;
            ScreenshotUploader uploader = new ScreenshotUploader(client, image);
            uploader.start();
            System.out.println("Скриншот сделан и отправлен в дропбокс");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}


    }
}
