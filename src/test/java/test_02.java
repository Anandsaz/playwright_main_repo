import com.microsoft.playwright.*;

import java.util.Arrays;
import java.util.List;

public class test_02 {
    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions().setViewportSize(null)
        );

        Page page = context.newPage();
        page.navigate("https://testautomationpractice.blogspot.com/");

        //New tab
//        Page popup = page.waitForPopup(() -> {
//            page.click("text=New Tab");
//        });
//        System.out.println("Title of the new tab: " + popup.title());
//        page.bringToFront();
//        popup.bringToFront();


        //Handle Single Popup Window
//        Page popup1 = page.waitForPopup(() -> {
//            page.click("text=Popup Windows");
//        });
//        System.out.println("Title : "+popup1.title());
//        popup1.close();

        // Handle Multiple Popup Window
//        Page popup = page.waitForPopup(() -> {
//            page.click("text=Popup Windows");
//        });
//        popup.waitForLoadState();
//        List<Page> pages = popup.context().pages();
//        for (Page p : pages) {
//            System.out.println("Title: " +p.title());
//            p.close();
//        }

        page.click("text=Popup Windows");
        page.waitForLoadState();
        List<Page> pages = page.context().pages();
        for (Page p : pages) {
            System.out.println("Title: " +p.title());
            p.close();
        }


    }
}
