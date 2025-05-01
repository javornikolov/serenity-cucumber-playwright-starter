package starter.search;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.demo.screenplay.playwright.interactions.Enter;
import org.demo.screenplay.playwright.interactions.Press;

public class LookForInformation {
    public static Performable about(String searchTerm) {
        return Task.where("{0} searches for '" + searchTerm + "'",
            Enter.theValue(searchTerm)
                .into(SearchForm.SEARCH_FIELD)
                .then(Press.keys("Enter"))
        );
    }
}
