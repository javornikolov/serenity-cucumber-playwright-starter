package org.demo.screenplay.playwright.interactions;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Press implements Performable {
  private final net.serenitybdd.screenplay.playwright.interactions.Press delegate;

  public static Press keys(String... keys) {
    return new Press(net.serenitybdd.screenplay.playwright.interactions.Press.keys(keys));
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    delegate.performAs(actor);
  }
}
