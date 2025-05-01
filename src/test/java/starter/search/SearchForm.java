package starter.search;

import org.demo.screenplay.playwright.targets.Target;

class SearchForm {
    static Target SEARCH_FIELD = Target.the("search field").locatedBy("#searchbox_input");
}
