package localization;

import geb.junit4.GebReportingTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import behaviour.Behaviour;

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
    public void givenWhenThenTest() {
    		Behaviour.given(() -> browser.to(HomePageSpanish.class))
    		.when((HomePageSpanish spanishPage) -> {
    			HomePageGerman german = spanishPage.getBrowser().to(HomePageGerman.class);
    			german.search("Haus");
    			return german;
    		})
    		.then((HomePageGerman germanPage) -> !germanPage.getWords().isEmpty());
    }
}
