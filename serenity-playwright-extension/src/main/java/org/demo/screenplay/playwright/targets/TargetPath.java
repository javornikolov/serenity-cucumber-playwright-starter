package org.demo.screenplay.playwright.targets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class TargetPath {
  private final List<String> selectors;

  public TargetPath(String... selectors) {
    if (selectors.length == 0) {
      throw new IllegalArgumentException("Selector chain must contain at least one selector");
    }

    this.selectors = List.of(selectors);
  }

  public List<String> getParentIframes() {
    return (selectors.size() == 1) ? List.of() : selectors.subList(0, selectors.size() - 1);
  }

  public String getNodeSelector() {
    return selectors.getLast();
  }
}
