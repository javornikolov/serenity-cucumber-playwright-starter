package org.demo.screenplay.playwright.interactions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
import org.demo.screenplay.playwright.targets.Target;

public class Click implements Performable {

  /**
   * Default constructor required by Screenplay
   */
  public Click() {
  }

  private Target target;
  private Locator.ClickOptions options;

  public Click(Target target) {
    this.target = target;
  }

  public static Click on(Target target) {
    return new Click(target);
  }

  public Performable withOptions(Locator.ClickOptions options) {
    this.options = options;
    return this;
  }

  @Override
  @Step("{0} clicks on #target")
  public <T extends Actor> void performAs(T actor) {
    Page page = BrowseTheWebWithPlaywright.as(actor).getCurrentPage();
    target.asLocator(page).click(options);
    BrowseTheWebWithPlaywright.as(actor).notifyScreenChange();
  }
}