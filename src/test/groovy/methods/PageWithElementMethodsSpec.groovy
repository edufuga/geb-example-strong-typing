package methods

import geb.spock.GebReportingSpec
import spock.lang.IgnoreRest

class PageWithElementMethodsSpec extends GebReportingSpec {

	def "simple test"() {
		when:
		assert browser
		PageWithElementMethods page = browser.to(PageWithElementMethods)

		then:
		page.searchBox.value("Test")
		page.search("Kugel")
		page.waitForSearchBox().value("warten")

		println page.wrapper.$("h1").text()
	}
}

