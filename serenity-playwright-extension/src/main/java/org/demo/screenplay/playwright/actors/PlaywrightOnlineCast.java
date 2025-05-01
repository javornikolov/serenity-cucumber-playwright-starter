package org.demo.screenplay.playwright.actors;

import com.microsoft.playwright.Page;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;

import java.util.function.Consumer;

public class PlaywrightOnlineCast extends Cast {

    public PlaywrightOnlineCast() {
        this(new Ability[]{});
    }

    public PlaywrightOnlineCast(Ability[] abilities) {
        super(abilities);
    }

    @SafeVarargs
    public PlaywrightOnlineCast(Consumer<Actor>... providers) {
        super(providers);
    }

    public static Cast whereEveryoneCan(Ability... abilities) {
        return new PlaywrightOnlineCast(abilities);
    }

    @SafeVarargs
    public static Cast whereEveryoneCan(Consumer<Actor>... abilityProviders) {
        return new PlaywrightOnlineCast(abilityProviders);
    }

    @Override
    public Actor actorNamed(String actorName, Ability... abilities) {

        Actor newActor = super.actorNamed(actorName, abilities);
        if (newActor.abilityTo(BrowseTheWebWithPlaywright.class) == null) {
            newActor.can(BrowseTheWebWithPlaywright.usingTheDefaultConfiguration());
        }
        return newActor;
    }

    public BrowsingActorBuilder actorUsingBrowser(String driver) {
        return new BrowsingActorBuilder(this, driver);
    }

    public static class BrowsingActorBuilder {

        private final Cast cast;
        private final String driver;

        public BrowsingActorBuilder(Cast cast, String driver) {
            this.cast = cast;
            this.driver = driver;
        }

        public Actor named(String actorName) {
            return cast.actorNamed(actorName,
                BrowseTheWebWithPlaywright.usingTheDefaultConfiguration().withBrowserType(driver));
        }

        private Page aConfiguredBrowser(String actorName) {
            return BrowseTheWebWithPlaywright.as(named(actorName)).withBrowserType(driver).getCurrentPage();
        }
    }
}
