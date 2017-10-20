package localization

import geb.spock.GebReportingSpec
import spock.lang.Ignore
import spock.lang.Unroll

class HomePageSpec extends GebReportingSpec {

	@Unroll
	def "simple test with '#pageClass' and '#word'"(Class pageClass, String word) {
		when: "I open the dictionary"
		assert browser
		HomePage page = browser.to(pageClass)

		then: "I search the word"
		page.checkTitle()
		page.checkSubTitle()

		page.search(word)

		where: "I use several localizations"
		pageClass << [HomePageSpanish, HomePageGerman]
		word << ["esfera", "Kugel"]
	}

	def "simple change of page"() {
		when:
		HomePageGerman page = browser.<HomePageGerman> to(HomePageGerman)
		Objects.requireNonNull(page, "The page has to exist.")
		page.run(new HomePageGermanToSpanishJavaAction())
		page.run(new HomePageGermanToSpanishGroovyAction())

		then:
		browser.at(HomePageSpanish)
	}
}
