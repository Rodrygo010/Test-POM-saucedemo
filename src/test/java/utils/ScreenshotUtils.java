package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String testName){

        String path = System.getProperty("user.dir") + "/screenshots/"
                + testName + "_" + System.currentTimeMillis() + ".png";

        try{
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
        } catch(Exception e){
            e.printStackTrace();
        }

        return path;
    }
}