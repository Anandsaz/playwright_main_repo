# Complete Window & Dialog Handling Solution

## Overview
This package provides robust and reusable utilities for handling windows, popups, tabs, and dialogs in Playwright QA tests.

---

## 📦 Files Delivered

### Test Files:
1. **WindowHandler.java** - Utility for managing windows and popups
2. **DialogHandler.java** - Utility for handling JavaScript dialogs (alerts, confirms, prompts)
3. **test_02.java** (Updated) - Your original test using WindowHandler
4. **test_multipe_windows.java** - Complete example demonstrating all window handling features

### Documentation Files:
1. **WINDOW_HANDLING_GUIDE.md** - Comprehensive guide with examples and best practices
2. **QUICK_START.md** - Quick reference guide
3. **README.md** (this file) - Complete overview

---

## 🚀 Quick Start

### Step 1: Use WindowHandler in Your Tests
```java
import java.util.Arrays;
import com.microsoft.playwright.*;

public class MyTest {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false)
        );
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        
        // Initialize WindowHandler
        WindowHandler windowHandler = new WindowHandler(context, page);
        
        page.navigate("https://example.com");
        
        // Open and handle popup
        Page popup = windowHandler.waitForPopup(() -> {
            page.click("button");
        });
        
        // Do something with popup
        System.out.println("Popup title: " + popup.title());
        
        // Close popup
        windowHandler.closePage(popup);
        
        // Cleanup
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}
```

### Step 2: Use DialogHandler for Dialogs
```java
// Handle alert
DialogHandler.handleAlert(page, () -> {
    page.click("button");
});

// Handle confirm
DialogHandler.handleConfirmYes(page, () -> {
    page.click("button");
});

// Handle prompt
DialogHandler.handlePrompt(page, "John", () -> {
    page.click("button");
});
```

---

## 📚 WindowHandler Methods

| Method | Purpose | Example |
|--------|---------|---------|
| `waitForPopup(action)` | Opens a new window | `Page p = wh.waitForPopup(() -> page.click("btn"));` |
| `getAllPages()` | Get all open pages | `List<Page> pages = wh.getAllPages();` |
| `switchToPage(index)` | Switch to page by index | `Page p = wh.switchToPage(1);` |
| `switchToPageByTitle(title)` | Switch to page by title | `Page p = wh.switchToPageByTitle("Google");` |
| `switchToPageByUrl(url)` | Switch to page by URL | `Page p = wh.switchToPageByUrl("google.com");` |
| `closePage(page)` | Close specific page | `wh.closePage(popup);` |
| `closeAllPopups()` | Close all non-main pages | `wh.closeAllPopups();` |
| `printAllPageDetails()` | Print all page details | `wh.printAllPageDetails();` |
| `getPageCount()` | Get total pages | `int count = wh.getPageCount();` |
| `getMainPage()` | Get main page | `Page main = wh.getMainPage();` |

---

## 📚 DialogHandler Methods

| Method | Purpose | Example |
|--------|---------|---------|
| `handleAlert(page, action)` | Handle JavaScript alert | `DialogHandler.handleAlert(page, () -> {...});` |
| `handleConfirmYes(page, action)` | Accept confirm dialog | `DialogHandler.handleConfirmYes(page, () -> {...});` |
| `handleConfirmNo(page, action)` | Reject confirm dialog | `DialogHandler.handleConfirmNo(page, () -> {...});` |
| `handlePrompt(page, input, action)` | Fill prompt with input | `DialogHandler.handlePrompt(page, "text", () -> {...});` |
| `handlePromptCancel(page, action)` | Cancel prompt dialog | `DialogHandler.handlePromptCancel(page, () -> {...});` |
| `handleMultipleDialogs(page, inputs, action)` | Handle multiple dialogs | `DialogHandler.handleMultipleDialogs(page, new String[]{"a", "b"}, () -> {...});` |

---

## 💡 Common Use Cases

### Use Case 1: Handle New Tab/Window
```java
WindowHandler wh = new WindowHandler(context, mainPage);
Page newTab = wh.waitForPopup(() -> {
    mainPage.click("text=Open in New Tab");
});
System.out.println("New tab URL: " + newTab.url());
wh.closePage(newTab);
```

### Use Case 2: Handle Multiple Popups
```java
Page popup1 = wh.waitForPopup(() -> mainPage.click("btn1"));
Page popup2 = wh.waitForPopup(() -> mainPage.click("btn2"));
System.out.println("Total windows: " + wh.getPageCount()); // 3
wh.closeAllPopups(); // Close both popups, keep main page
```

### Use Case 3: Switch Between Windows
```java
Page target = wh.switchToPageByTitle("Target Title");
if (target != null) {
    target.fill("input", "value");
    target.click("button");
}
```

### Use Case 4: Handle JavaScript Dialogs
```java
// Alert
DialogHandler.handleAlert(page, () -> {
    page.click("text=Show Alert");
});

// Confirm
DialogHandler.handleConfirmYes(page, () -> {
    page.click("text=Delete");
});

// Prompt
DialogHandler.handlePrompt(page, "John Doe", () -> {
    page.click("text=Enter Name");
});
```

---

## ✅ Best Practices

1. **Initialize Early**: Create WindowHandler right after creating your main page
2. **Check for Null**: Always verify that switched pages exist
3. **Debug with Print**: Use `printAllPageDetails()` to debug window issues
4. **Clean Up**: Always close pages and browser resources
5. **Sequential Operations**: Handle popups immediately after triggering them
6. **Use Specific Switches**: Prefer `switchToPageByUrl()` over `switchToPageByTitle()` for uniqueness

---

## 🔧 Installation

1. Copy the following files to `src/test/java/`:
   - WindowHandler.java
   - DialogHandler.java
   - (Optional) test_multipe_windows.java for reference

2. Update your existing tests to use these utilities

3. Build and run:
```bash
mvn clean compile
mvn test
```

---

## 📖 Documentation Files

- **WINDOW_HANDLING_GUIDE.md** - Detailed API documentation and examples
- **QUICK_START.md** - Quick reference guide
- **README.md** - This file (overview)

---

## 🐛 Troubleshooting

| Problem | Solution |
|---------|----------|
| Popup never detected | Ensure the action actually triggers a popup/new tab |
| NullPointerException | Check if page exists: `if (page != null)` |
| Cannot find page by title | Some popups might not have a title; use URL instead |
| Popup closes immediately | Capture reference immediately after `waitForPopup()` |
| Multiple dialogs | Use `handleMultipleDialogs()` for sequential dialogs |

---

## 📝 Example Test File Structure

```java
import com.microsoft.playwright.*;
import java.util.Arrays;

public class MyTest {
    public static void main(String[] args) {
        // 1. Setup
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(/* options */);
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        
        // 2. Initialize handlers
        WindowHandler wh = new WindowHandler(context, page);
        
        // 3. Test logic
        page.navigate("https://example.com");
        Page popup = wh.waitForPopup(() -> page.click("btn"));
        
        // 4. Assertions & Verification
        assert popup.title().contains("expected");
        
        // 5. Cleanup
        wh.closePage(popup);
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}
```

---

## 🎯 Next Steps

1. Review **WINDOW_HANDLING_GUIDE.md** for detailed examples
2. Check **test_multipe_windows.java** for comprehensive usage patterns
3. Update your existing tests to use these utilities
4. Run the tests with: `mvn test -Dtest=test_multipe_windows`

---

## 📞 Support

For issues or questions:
1. Check WINDOW_HANDLING_GUIDE.md
2. Review test_multipe_windows.java for examples
3. Refer to [Playwright Java Docs](https://playwright.dev/java/)

---

## ✨ Features Provided

✅ Multi-window/popup management
✅ Switch between windows by index, title, or URL
✅ Dialog (alert, confirm, prompt) handling
✅ Page listing and debugging
✅ Resource cleanup utilities
✅ Production-ready code
✅ Comprehensive documentation
✅ Ready-to-use example tests

---

**Created:** May 2026
**Playwright Version:** Compatible with 1.59.0+
**Java Version:** 8+

