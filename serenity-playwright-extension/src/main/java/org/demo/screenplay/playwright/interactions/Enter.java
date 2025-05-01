package org.demo.screenplay.playwright.interactions;

import com.microsoft.playwright.Locator;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
import org.demo.screenplay.playwright.targets.Target;

public class Enter implements Performable {

  /**
   * Default constructor required by Screenplay
   */
  public Enter() {
  }

  private String value;
  private Target target;
  private Locator.FillOptions options;

  public Enter(String value) {
    this.value = value;
  }

  public static Enter theValue(String value) {
    return new Enter(value);
  }

  public Performable into(String selector) {
    this.target = Target.the(selector).locatedBy(selector);
    return this;
  }

  public Performable into(Target target) {
    this.target = target;
    return this;
  }

  public Performable withOptions(Locator.FillOptions options) {
    this.options = options;
    return this;
  }

  @Override
  @Step("{0} enters #value into #target")
  public <T extends Actor> void performAs(T actor) {
    target.asLocator(BrowseTheWebWithPlaywright.as(actor).getCurrentPage()).fill(value, options);
    BrowseTheWebWithPlaywright.as(actor).notifyScreenChange();
  }
}
