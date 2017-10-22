package localization;

import geb.junit4.GebReportingTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import behaviour.Behaviour;

import static behaviour.Behaviour.given;

@RunWith(JUnit4.class)
public class HomePageTest extends GebReportingTest {

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
    public void givenWhenThenTestWordList() {
		assertTrue("The list of words is empty",
			given(() -> browser.to(HomePageSpanish.class))
			.when(spanishPage -> {
	    		HomePageGerman german = spanishPage.getBrowser().to(HomePageGerman.class);
	    		german.search("Haus");
	    		return german;
	    	})
	    	.then(germanPage -> !germanPage.getWords().isEmpty()));
    }

    @Test
    public void givenWhenThenTestSpanishTitle() {
		assertTrue("The spanish title is empty",
			given(() -> browser.to(HomePageGerman.class))
			.when(german -> new HomePageGermanToSpanishJavaAction().run(german))
			.then(page -> !page.getMainTitle().isEmpty()));
    }
}
