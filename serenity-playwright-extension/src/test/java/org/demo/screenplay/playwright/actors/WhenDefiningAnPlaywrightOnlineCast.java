package org.demo.screenplay.playwright.actors;

import static org.assertj.core.api.Assertions.assertThat;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.playwright.abilities.ActorCannotUsePlaywrightException;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
import org.junit.Test;

import java.util.function.Consumer;

public class WhenDefiningAnPlaywrightOnlineCast {

    @Test
    public void shouldHaveTheAbilityToBrowseTheWeb() {
        Cast cast = new PlaywrightOnlineCast();
        Actor joe = cast.actorNamed("Joe");
        assertThat(BrowseTheWebWithPlaywright.as(joe)).isNotNull();
    }

    static class MyAbility implements Ability {}
    static class MyOtherAbility implements Ability {}

    @Test
    public void onlineCastMembersCanHaveOtherAbilities() {

        Cast cast = PlaywrightOnlineCast.whereEveryoneCan(new MyAbility(), new MyOtherAbility());
        Actor joe = cast.actorNamed("Joe");

        assertThat(BrowseTheWebWithPlaywright.as(joe)).isNotNull();
        assertThat(joe.abilityTo(MyAbility.class)).isNotNull();
        assertThat(joe.abilityTo(MyOtherAbility.class)).isNotNull();
    }

    static class Fetch implements Ability {
        final String item;
        private int counter = 1;

        Fetch(String item) {
            this.item = item;
        }

        public static Fetch some(String item) {
            return new Fetch(item);
        }

        public String deliverItem() {
            return item + " #" + counter++;
        }
    }

    @Test
    public void canDoArbitraryThings() {
        Consumer<Actor> fetchTheCoffee = actor -> actor.whoCan(Fetch.some("Coffee"));
        Cast globeTheatreCast = PlaywrightOnlineCast.whereEveryoneCan(fetchTheCoffee);
        Actor kenneth = globeTheatreCast.actorNamed("Kenneth");
        assertThat(BrowseTheWebWithPlaywright.as(kenneth)).isNotNull();
        assertThat(kenneth.abilityTo(Fetch.class).item).isEqualTo("Coffee");

    }

    @Test(expected = ActorCannotUsePlaywrightException.class)
    public void weCanDefineANonWebCast() {
        Cast cast = Cast.whereEveryoneCan(new MyAbility());
        Actor joe = cast.actorNamed("Joe");
        assertThat(joe.abilityTo(MyAbility.class)).isNotNull();
        assertThat(BrowseTheWebWithPlaywright.as(joe)).isNull();
    }

    @Test(expected = ActorCannotUsePlaywrightException.class)
    public void weCanDefineACastOfStandardActors() {
        Cast cast = Cast.ofStandardActors();
        Actor joe = cast.actorNamed("Joe");
        assertThat(BrowseTheWebWithPlaywright.as(joe)).isNull();
    }
}
