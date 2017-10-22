package localization;

import geb.junit4.GebReportingTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static behaviour.Behaviour.given;

@RunWith(JUnit4.class)
public class HomePageTest extends GebReportingTest {

    @Test
    public void simpleChangeFromSpanishToGerman() {
    	// when
		HomePageSpanish page = browser.<HomePageSpanish> to(HomePageSpanish.class);
		// page.run(new HomePageGermanToSpanishGroovyAction()); // Java power.
		page.run(new HomePageSpanishToGermanAction());

		// then
		browser.at(HomePageGerman.class);
    }

    @Test
    public void thereAndBackAgain() {
    	browser.to(HomePageSpanish.class)
    		.run(new HomePageSpanishToGermanAction())
    		.run(new HomePageGermanToSpanishJavaAction());
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
