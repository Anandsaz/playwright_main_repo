# Quick Reference - Window Handling Code

## Summary
I've created a complete window handling solution for your Playwright QA project with 3 new files:

## Files Created:

### 1. **WindowHandler.java** (Reusable Utility Class)
- Manages multiple windows, popups, and tabs
- Key methods:
  - `waitForPopup(action)` - Open new window
  - `switchToPage(index)` - Switch to window by index
  - `switchToPageByTitle(title)` - Switch by window title
  - `switchToPageByUrl(url)` - Switch by URL
  - `closePage(page)` - Close specific window
  - `closeAllPopups()` - Close all popups except main
  - `printAllPageDetails()` - Debug view of all windows
  - `getPageCount()` - Get total number of windows

### 2. **test_multipe_windows.java** (Complete Example)
- Shows how to use WindowHandler with various scenarios:
  - Opening and handling single popup
  - Switching between multiple windows
  - Closing all popups at once
  - Switching by URL

### 3. **test_02.java** (Updated)
- Your original test now uses the WindowHandler utility
- Much cleaner and more maintainable code
- Includes proper resource cleanup

### 4. **WINDOW_HANDLING_GUIDE.md** (Complete Documentation)
- Detailed API documentation
- 5+ example scenarios
- Best practices and troubleshooting
- Common patterns and usage examples

---

## Quick Start Example:

```java
// Initialize
WindowHandler windowHandler = new WindowHandler(context, mainPage);

// Open popup
Page popup = windowHandler.waitForPopup(() -> {
    mainPage.click("text=New Tab");
});

// Use popup
System.out.println("Popup title: " + popup.title());
windowHandler.printAllPageDetails();

// Close popup
windowHandler.closePage(popup);
```

---

## How to Use in Your Tests:

1. **Copy WindowHandler.java** into your test sources
2. **Initialize it** in your test classes: `WindowHandler handler = new WindowHandler(context, page);`
3. **Use the methods** to manage windows/popups
4. **Refer to WINDOW_HANDLING_GUIDE.md** for detailed usage patterns

---

## Compilation & Execution:

```bash
# Compile the project
mvn clean compile

# Run the example test
mvn test -Dtest=test_multipe_windows

# Run your updated test_02
mvn test -Dtest=test_02
```

---

## Key Features:

✅ Handle multiple windows/popups/tabs
✅ Switch between windows by index, title, or URL
✅ Proper resource management
✅ Debug information logging
✅ Reusable utility class for all tests
✅ Comprehensive documentation
✅ Production-ready code

---

All files are ready to use in your Playwright QA project!

