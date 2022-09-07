package com.gemini.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.gemini.reporting.GemTestReporter;
import com.gemini.reporting.STATUS;
import com.gemini.utils.GemJarGlobalVar;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MobileAction extends MobileGenericUtils {

    public static void mobileProperty() {

        String rootDirectory = System.getProperty("user.dir");
        String resourceDirectory = rootDirectory + "/src/test/resources/";

        //Configure property File

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(resourceDirectory + "AppiumSample.properties"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        MobileGlobalVar.appiumProperties = properties;
    }


    public static WebElement getElement(By locator) {
        try {
            WebElement element = (WebElement) MobileDriverManager.getAppiumDriver().findElement(locator);
            return element;
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static String getElementText(By locator) {
        try {
            WebElement element = getElement(locator);
            String elementText = element.getText();
            return elementText;
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static void click(By locator) {
        try {
            WebElement element = getElement(locator);
            element.click();
        } catch (Exception var2) {
            GemTestReporter.addTestStep("Some error occur while Click", "Error Occur", STATUS.FAIL);
            var2.printStackTrace();
        }
    }

    public static void click(By locator, String elementLabel) throws IOException {
        try {
            WebElement element = getElement(locator);
            element.click();
            GemTestReporter.addTestStep("Click on ", "Click Successful on " + elementLabel, STATUS.PASS);
                  //  MobileAction.takeSnapShot());

        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Click on ", "Click Failed on " + elementLabel, STATUS.FAIL);
                  //  MobileAction.takeSnapShot());
        }
    }


    public static void typeText(By locator, String textToEnter) {
        try {
            WebElement element = getElement(locator);
            element.sendKeys(textToEnter);
        } catch (Exception var2) {
            //GemTestReporter.addTestStep("Some error occur while Click", "Error Occur", STATUS.FAIL, takeSnapShot());
            var2.printStackTrace();
        }
    }

    public static void tap(By locator) {
        try {
            TouchActions action = new TouchActions(MobileDriverManager.getAppiumDriver());
            WebElement element = getElement(locator);
            action.singleTap(element);
            action.perform();
        } catch (Exception var2) {
            //GemTestReporter.addTestStep("Some error occur while Tap", "Error Occur", STATUS.FAIL, takeSnapShot());
            var2.printStackTrace();
        }
    }


    public static void doubleTap(By locator) {
        try {
            TouchActions action = new TouchActions(MobileDriverManager.getAppiumDriver());
            WebElement element = getElement(locator);
            action.doubleTap(element);
            action.perform();
        } catch (Exception var2) {
            //GemTestReporter.addTestStep("Some error occur while Tap", "Error Occur", STATUS.FAIL, takeSnapShot());
            var2.printStackTrace();
        }
    }

    public static void move(int X, int Y) {
        try {
            TouchActions action = new TouchActions(MobileDriverManager.getAppiumDriver());
            //action.down(10, 10);
            action.move(X, Y);
            action.perform();
        } catch (Exception var2) {
            //GemTestReporter.addTestStep("Some error occur while Tap", "Error Occur", STATUS.FAIL, takeSnapShot());
            var2.printStackTrace();
        }
    }

    public static void moveUp(int X, int Y) {
        try {
            TouchActions action = new TouchActions(MobileDriverManager.getAppiumDriver());
            //action.down(10, 10);
            action.up(X, Y);
            action.perform();
        } catch (Exception var2) {
            //GemTestReporter.addTestStep("Some error occur while Tap", "Error Occur", STATUS.FAIL, takeSnapShot());
            var2.printStackTrace();
        }
    }

    public static void moveDown(int X, int Y) {
        try {
            TouchActions action = new TouchActions(MobileDriverManager.getAppiumDriver());
            //action.down(10, 10);
            action.down(X, Y);
            action.perform();
        } catch (Exception var2) {
            //GemTestReporter.addTestStep("Some error occur while Tap", "Error Occur", STATUS.FAIL, takeSnapShot());
            var2.printStackTrace();
        }
    }

    public static void longPress(By locator) {
        try {
            TouchActions action = new TouchActions(MobileDriverManager.getAppiumDriver());
            WebElement element = getElement(locator);
            action.longPress(element);
            action.perform();


        } catch (Exception var2) {
            //GemTestReporter.addTestStep("Some error occur while Tap", "Error Occur", STATUS.FAIL, takeSnapShot());
            var2.printStackTrace();
        }
    }


    public static void waitSec(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setImplicitTimeOut(long seconds) {
        try {
            MobileDriverManager.getAppiumDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setScriptTimeOut(long seconds) {

        try {
            MobileDriverManager.getAppiumDriver().manage().timeouts().setScriptTimeout(seconds, TimeUnit.SECONDS); // seconds is being converted into Timeunit.SECONDS
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPageLoadTimeOut(long seconds) {
        try {
            MobileDriverManager.getAppiumDriver().manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
////	////////////////////////////////////////NAVIGATION//////////////////////////////////////////////////////


    public static void navigateBack(Boolean report) {
        try {
            MobileDriverManager.getAppiumDriver().navigate().back();
//	            if (report) {
//	                GemTestReporter.addTestStep("Navigate Back", "Navigate Back Successful", STATUS.PASS, DriverAction.takeSnapShot());
//	            }
        } catch (Exception e) {
//	            GemTestReporter.addTestStep("Navigate Back", "Navigate Back Failed", STATUS.FAIL, DriverAction.takeSnapShot());
            e.printStackTrace();
        }
    }

    public static void refresh(Boolean report) {
        try {
            MobileDriverManager.getAppiumDriver().navigate().refresh();
//	            if (report) {
//	                GemTestReporter.addTestStep("Refresh Page", "Page Refresh Successful", STATUS.PASS, DriverAction.takeSnapShot());
//	            }
        } catch (Exception e) {
//	            GemTestReporter.addTestStep("Refresh Page", "Page Refresh Failed", STATUS.FAIL, DriverAction.takeSnapShot());
            e.printStackTrace();
        }
    }

    public static void navigateForward(Boolean report) {
        try {
            MobileDriverManager.getAppiumDriver().navigate().forward();
//	            if (report) {
//	                GemTestReporter.addTestStep("Navigate Forward", "Forward Navigation Successful", STATUS.PASS, DriverAction.takeSnapShot());
            //
        } catch (Exception e) {
//	            GemTestReporter.addTestStep("Navigate Forward", "Forward Navigation Failed", STATUS.FAIL, DriverAction.takeSnapShot());
            e.printStackTrace();
        }
    }

    public static void navigateToUrl(String url, Boolean report) {
        try {
            MobileDriverManager.getAppiumDriver().navigate().to(url);
//	                 if (report){
//	                     GemTestReporter.addTestStep("Navigate Forward", "Forward Navigation Successful", STATUS.PASS, DriverAction.takeSnapShot());
            //
//	                 }
        } catch (Exception e) {
//	                GemTestReporter.addTestStep("Navigate Forward", "Forward Navigation Successful", STATUS.PASS, DriverAction.takeSnapShot());
            e.printStackTrace();
        }

    }

    public static void LaunchURL(String url, Boolean report) {
        try {
            MobileDriverManager.getAppiumDriver().get(url);
//	            if (report){
//	                  GemTestReporter.addTestStep("URL launch ", "URL launch Successful", STATUS.PASS, DriverAction.takeSnapShot());
            //
////	                 }
        } catch (Exception e) {
//	            GemTestReporter.addTestStep("URL launch ", "URL launch Successful", STATUS.PASS, DriverAction.takeSnapShot());
            e.printStackTrace();
        }
    }
    ////////////////////////Mouse///////////////////////////////////////////////////////

    public static void doubleClick(WebElement element) {
        try {
            Actions act = new Actions(MobileDriverManager.getAppiumDriver());
            act.doubleClick(element).perform();
//	            GemTestReporter.addTestStep("Double Click on ", "Double Click Successful on " + elementLabel, STATUS.PASS,
//	                    DriverAction.takeSnapShot());
        } catch (Exception e) {
            e.printStackTrace();
//	            GemTestReporter.addTestStep("Double Click on ", "Double Click Failed on " + elementLabel, STATUS.FAIL,
//	                    DriverAction.takeSnapShot());
        }
    }

    public static WebElement scrollToElement(String text) {
        try {
            MobileDriverManager.getAppiumDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView" +
                    "(text(\"" + text + "\"));"));
        } catch (Exception var2) {
            var2.printStackTrace();
        }
        return null;
    }
    //    moveToElement -> Move the mouse by an offset of the specificed element
    public static void moveToElement(By locator,int xOffset,int yOffset){
        try{
            Actions act = new Actions(MobileDriverManager.getAppiumDriver());
            WebElement element = MobileDriverManager.getAppiumDriver().findElement(locator);
            act.moveToElement(element,xOffset,yOffset);
            act.perform();
//            GemTestReporter.addTestStep("MoveToElement ", "MoveToElement Successful , STATUS.PASS,
//                  DriverAction.takeSnapShot());

        }catch (Exception e){
//            GemTestReporter.addTestStep("MoveToElement", "MoveToElement Failed on " , STATUS.FAIL,
//                   DriverAction.takeSnapShot());
            e.printStackTrace();
        }

    }

    public static void moveToElement(WebElement element,int xOffset,int yOffset){
        try {
            Actions act= new Actions(MobileDriverManager.getAppiumDriver());
            act.moveToElement(element,xOffset,yOffset);
            //        GemTestReporter.addTestStep("MoveToElement ", "MoveToElement Successful , STATUS.PASS,
//                  DriverAction.takeSnapShot());

        }catch (Exception e){
            //            GemTestReporter.addTestStep("MoveToElement", "MoveToElement Failed on " , STATUS.FAIL,
//                   DriverAction.takeSnapShot());
            e.printStackTrace();
        }
    }

    // ButtonDown->Click and hold the left mouse button at the current mouse coordinates
    public static void buttonDown(By locator ){
        try {
            Actions act = new Actions(MobileDriverManager.getAppiumDriver());
            WebElement element =  MobileDriverManager.getAppiumDriver().findElement(locator);
            act.moveToElement(element);
            act.clickAndHold();
            act.perform();
            //        GemTestReporter.addTestStep("ButtonDown", "ButtonDown Successful , STATUS.PASS,
//                  DriverAction.takeSnapShot());
        }catch (Exception e){
            e.printStackTrace();
            //            GemTestReporter.addTestStep("ButtonDown", "ButtonDown Failed on " , STATUS.FAIL,
//                   DriverAction.takeSnapShot());
        }
    }
    // ButtonUp->Releases the mouse button previously held
//    Must be called after the buttonDown
    public static void buttonUp(By locator, int xOffset,int yOffset){
        try{
            Actions act = new Actions(MobileDriverManager.getAppiumDriver());
            WebElement element= MobileDriverManager.getAppiumDriver().findElement(locator);
            act.moveToElement(element);
            act.clickAndHold();
            act.moveToElement(element, xOffset,yOffset);
            act.release();
            act.perform();
            //        GemTestReporter.addTestStep("ButtonUp", "ButtonUp Successful , STATUS.PASS,
//                  DriverAction.takeSnapShot());
        }
        catch (Exception e){
            e.printStackTrace();
            //            GemTestReporter.addTestStep("ButtonUp", "ButtonUp Failed on " , STATUS.FAIL,
//                   DriverAction.takeSnapShot());
        }
    }

    public static void buttonUp(WebElement element,int xOffset,int yOffset){
        try{
            Actions action = new Actions(MobileDriverManager.getAppiumDriver());
            action.moveToElement(element);
            action.clickAndHold();
            action.moveToElement(element, xOffset,yOffset);
            action.release();
            action.perform();
            //        GemTestReporter.addTestStep("ButtonUp", "ButtonUp Successful , STATUS.PASS,
//                  DriverAction.takeSnapShot());
        } catch (Exception e){
            e.printStackTrace();
            //            GemTestReporter.addTestStep("ButtonUp", "ButtonUp Failed on " , STATUS.FAIL,
//                   DriverAction.takeSnapShot());
        }
    }

    /////////////////////////CLEAR/////////////////////////////////////////
    public static void clear(By locator){
        try {
            WebElement element = MobileDriverManager.getAppiumDriver().findElement(locator);
            element.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void lockDevice  (){
        try{

            ((AndroidDriver) MobileDriverManager.getAppiumDriver()).lockDevice();

            // getAppiumDriver.lockDevice();
            // ((AndroidDriver) MobileDriverManager.getAppiumDriver()).lockDevice();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void pressBackButton(){
        try {
            ((AndroidDriver) MobileDriverManager.getAppiumDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
//            ((AndroidDriver) MobileDriverManager.getAppiumDriver()).pressKey(new KeyEvent(AndroidKey.BACK));;
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public static void pressHomeButton(){
        try {
            ((AndroidDriver) MobileDriverManager.getAppiumDriver()).pressKey(new KeyEvent(AndroidKey.HOME));
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }
/////////////////////////////////////////////DRAGandDrop////////////////////////////////

    public static void dragAndDrop(WebElement from, WebElement To, boolean report)  {
        try {
            WebElement from1 = MobileDriverManager.getAppiumDriver().findElement((By) from);
            WebElement To1 = MobileDriverManager.getAppiumDriver().findElement((By) To);
            Actions act = new Actions(MobileDriverManager.getAppiumDriver());
            act.dragAndDrop(from1, To1).build().perform();
//            if (report) {
//                GemTestReporter.addTestStep("Drag and Drop", "Success", STATUS.PASS, DriverAction.takeSnapShot());
//            }
        } catch (Exception e) {
            e.printStackTrace();
//            GemTestReporter.addTestStep("Some error occur while Drag and drop", "Error Occur", STATUS.FAIL,
//                    DriverAction.takeSnapShot());
        }
    }
    // upload file
    public static void fileUpload(WebElement element, String path)  {
        try {
//            MobileDriverManager.getAppiumDriver().getElement((By) element).sendKeys(path);
            getElement((By) element).sendKeys(path);
//            GemTestReporter.addTestStep("File Upload", "Successfully", STATUS.PASS, takeSnapShot());
        } catch (Exception e) {
            e.printStackTrace();
//            GemTestReporter.addTestStep("Some error occur", "Error Occur", STATUS.FAIL,
//            MobileDriverManager.getAppiumDriver().takeSnapShot());
        }
    }



    public static String takeSnapShot() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileWithPath;
        if(GemJarGlobalVar.reportLocation!=null) {
            fileWithPath = GemJarGlobalVar.reportLocation + "/SS/SS" + timestamp.getTime() + ".png";
        } else {
            fileWithPath = "report" + "/SS/SS" + timestamp.getTime() + ".png";
        }
        WebDriver webdriver = MobileDriverManager.getAppiumDriver();
        TakesScreenshot scrShot = (TakesScreenshot)webdriver;
        File SrcFile = (File)scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);

        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return "SS/SS" + timestamp.getTime() + ".png";
    }



}




