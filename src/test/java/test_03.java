import com.microsoft.playwright.*;

import java.util.Arrays;

public class test_03 {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize((null)));
        Page page = context.newPage();
        System.out.println("Automation");
        System.out.println("Automation line added");
        page.navigate("https://testautomationpractice.blogspot.com/");

        System.out.println("Automation line added");

    }
}