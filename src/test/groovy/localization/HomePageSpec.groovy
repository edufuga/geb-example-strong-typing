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

	def "simple change of page from German to Spanish"() {
		when:
		HomePageGerman page = browser.<HomePageGerman> to(HomePageGerman)
		page.run(new HomePageGermanToSpanishJavaAction())
		page.run(new HomePageGermanToSpanishGroovyAction())

		then:
		browser.at(HomePageSpanish)
	}

	def "simple change of page from Spanish to German"() {
		when:
		HomePageSpanish page = browser.<HomePageSpanish> to(HomePageSpanish)
		page.run(new HomePageSpanishToGermanAction())

		then:
		browser.at(HomePageGerman)
	}

}
