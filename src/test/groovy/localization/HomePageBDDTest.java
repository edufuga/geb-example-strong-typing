package localization;

import geb.junit4.GebReportingTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import behaviour.Behaviour;

import static behaviour.Behaviour.given;

@RunWith(JUnit4.class)
public class HomePageBDDTest extends GebReportingTest {

    @Test
    public void simpleChangeFromSpanishToGerman() {
    	assertTrue(new Behaviour(browser).given(browser -> browser.to(HomePageSpanish.class))
    	.when(page -> page.run(new HomePageSpanishToGermanAction()))
    	.then(HomePageGerman::verifyAt));
    }

    @Test
    public void thereAndBackAgain() {
    	assertTrue(given(() ->	browser.to(HomePageSpanish.class))
    	.when(spanish -> spanish.run(new HomePageSpanishToGermanAction()))
    	.then(german -> german.run(new HomePageGermanToSpanishJavaAction()).verifyAt()));
    }

    @Test
    public void wordList() {
		assertTrue("The list of words is empty",
			given(() -> browser.to(HomePageSpanish.class))
			.when(spanishPage -> {
				spanishPage.search("asac");
				return spanishPage;
			})
			.when(spanishPage -> {
				spanishPage.search("casa");
				return spanishPage;
			})
			.then(spanishPage -> !spanishPage.getWords().isEmpty()));
    }

    @Test
    public void spanishTitle() {
		assertTrue("The spanish title is empty",
			given(() -> browser.to(HomePageSpanish.class))
			.then(page -> !page.getMainTitle().isEmpty()));
    }
}
