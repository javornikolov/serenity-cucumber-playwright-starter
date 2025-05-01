package starter.search;

import org.demo.screenplay.playwright.targets.Target;

public class SearchArticle {
    public static final Target BODY =  Target.the("article identifier").locatedBy("//article");
}
