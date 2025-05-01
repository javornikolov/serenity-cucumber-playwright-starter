package org.demo.screenplay.playwright.targets;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Target {
  private final String label;
  private final TargetPath targetPath;

  /**
   * Multiple selectors in case we're nested in iframes
   */
  public Target(String label, String... selectors) {
    this.label = label;
    this.targetPath = new TargetPath(selectors);
  }

  public static TargetBuilder the(String label) {
    return new TargetBuilder(label);
  }

  public static class TargetBuilder {

    private final String label;

    public TargetBuilder(String label) {
      this.label = label;
    }

    public Target locatedBy(String... selectors) {
      return new Target(label, selectors);
    }
  }

  public TargetPath asSelectorChain() {
    return targetPath;
  }

  public Locator asLocator(Page page) {
    FrameLocator parentFrame = null;

    for (String selector : targetPath.getParentIframes()) {
      if (parentFrame == null) {
        parentFrame = page.frameLocator(selector);
      } else {
        parentFrame = parentFrame.frameLocator(selector);
      }
    }

    if (parentFrame != null) {
      return parentFrame.locator(targetPath.getNodeSelector());
    } else {
      return page.locator(targetPath.getNodeSelector());
    }
  }

  @Override
  public String toString() {
    return label;
  }
}

