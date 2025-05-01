package org.demo.screenplay.playwright.interactions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
import org.demo.screenplay.playwright.targets.Target;

import java.util.function.Consumer;

public class WaitFor implements Performable {

  /**
   * Default constructor required by Screenplay
   */
  public WaitFor() {
  }

  private Target target;
  private Locator.WaitForOptions options;
  Consumer<Locator> nextAction;

  public WaitFor(Target target) {
    this.target = target;
  }

  /**
   * Wait for an element to be in a given state.
   */
  public static WaitFor selector(Target target) {
    return new WaitFor(target);
  }

  public static Performable url(String url) {
    return new WaitForUrl(url);
  }

  public static Performable loadState(LoadState loadState) {
    return new WaitForLoadState(loadState);
  }

  public WaitFor withOptions(Locator.WaitForOptions options) {
    this.options = options;
    return this;
  }

  @Override
  @Step("{0} waits for #target")
  public <T extends Actor> void performAs(T actor) {
    Locator locator = target.asLocator(BrowseTheWebWithPlaywright.as(actor).getCurrentPage());
    locator.waitFor(options);

    if (nextAction != null) {
      nextAction.accept(locator);
    }
  }

  public Performable andThen(Consumer<Locator> nextAction) {
    this.nextAction = nextAction;
    return this;
  }

  @RequiredArgsConstructor
  public static class WaitForUrl implements Performable {
    private final String url;

    @Override
    public <T extends Actor> void performAs(T actor) {
      BrowseTheWebWithPlaywright.as(actor).getCurrentPage().waitForURL(url);
    }
  }

  @RequiredArgsConstructor
  public static class WaitForLoadState implements Performable {
    private final LoadState loadState;

    @Override
    public <T extends Actor> void performAs(T actor) {
      BrowseTheWebWithPlaywright.as(actor).getCurrentPage().waitForLoadState(loadState);
    }
  }
}