package starter.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.demo.screenplay.playwright.interactions.Open;


public class Navigate {
    public static Performable to(String url) {
        return Task.where("{0} opens the url #url",
                Open.url(url));
    }
}
