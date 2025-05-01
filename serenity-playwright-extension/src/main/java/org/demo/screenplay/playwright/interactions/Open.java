package org.demo.screenplay.playwright.interactions;

import net.serenitybdd.screenplay.playwright.interactions.OpenUrl;

public class Open {
  public static OpenUrl url(String url) {
    return new OpenUrl(url);
  }
}