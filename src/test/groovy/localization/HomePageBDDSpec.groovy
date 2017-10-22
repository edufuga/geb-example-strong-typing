package localization

import static behaviour.Behaviour.given
import static org.junit.Assert.assertTrue

import org.junit.Test

import behaviour.Behaviour
import geb.Browser
import geb.spock.GebReportingSpec
import spock.lang.Unroll

class HomePageBDDSpec extends GebReportingSpec {

    def "simple change from spanish to german"() {
		expect:
		given { browser.to(HomePageSpanish) }
    	.when { HomePageSpanish page -> page.run(new HomePageSpanishToGermanAction()) }
    	.then { HomePageGerman page -> page.verifyAt() }
    }

	def "there and back again"() {
		expect:
		given { browser.to(HomePageSpanish) }
		.when { HomePageSpanish spanish -> spanish.run(new HomePageSpanishToGermanAction()) }
		.then { HomePageGerman german -> german.run(new HomePageGermanToSpanishJavaAction()).verifyAt() }
	}

    def "word list"() {
		expect:
		given { browser.to(HomePageSpanish) }
		.when { HomePageSpanish spanishPage -> 
				spanishPage.search("asac");
				return spanishPage;
		}
		.when { HomePageSpanish spanishPage ->
				spanishPage.search("casa");
				return spanishPage;
		}
		.then { HomePageSpanish spanishPage -> !spanishPage.getWords().isEmpty() }
    }

    def "spanish title"() {
		expect:
		given { browser.to(HomePageSpanish) }
		.then { HomePageSpanish page -> !page.getMainTitle().isEmpty() }
    }

}
