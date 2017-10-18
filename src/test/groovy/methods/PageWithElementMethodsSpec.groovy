package methods

import geb.spock.GebReportingSpec
import static module.DictionaryCategory.*
import spock.lang.IgnoreRest

class PageWithElementMethodsSpec extends GebReportingSpec {

	def "simple test"() {
		when:
		assert browser
		PageWithElementMethods page = browser.to(PageWithElementMethods)

		then:
		page.searchBox.value("Test")
		page.search("Kugel")
		println page.menu(1).text()
		println page.waitedMenu(2).text()
		println page.wrapper.$("h1").text()
		println page.category(DICTIONARY).text()
		println page.dictionary.text()
	}
}

