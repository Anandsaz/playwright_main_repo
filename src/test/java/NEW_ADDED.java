import com.microsoft.playwright.*;

import java.util.Arrays;

public class NEW_ADDED {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize((null)));
        Page page = context.newPage();
        page.navigate("https://testautomationpractice.blogspot.com/");


    }
}
