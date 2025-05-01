package starter.stepdefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.microsoft.playwright.options.LoadState;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.playwright.questions.TheWebPage;
import org.demo.screenplay.playwright.interactions.WaitFor;
import starter.navigation.DuckDuckGoHomePage;
import starter.navigation.Navigate;
import starter.search.LookForInformation;

public class SearchStepDefinitions {
    @Given("{actor} is researching things on the internet")
    public void sergeyIsResearchingThingsOnTheInternet(Actor actor) {
        actor.wasAbleTo(Navigate.to(DuckDuckGoHomePage.HOME_PAGE_URL));
    }

    @When("he looks up {string}")
    public void heLooksUp(String searchTerm) {
        theActorInTheSpotlight().attemptsTo(
            LookForInformation.about(searchTerm)
        );
    }

    @Then("he should see information about {string}")
    public void heShouldSeeInformationAbout(String term) {
        theActorInTheSpotlight().attemptsTo(
            WaitFor.url("**/*q=" + term + "*"),
            WaitFor.loadState(LoadState.DOMCONTENTLOADED),
            Ensure.that(TheWebPage.title()).containsIgnoringCase(term)
        );
    }
}
