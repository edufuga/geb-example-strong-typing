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
		println page.menu(1).text()

		println page.wrapper.$("h1").text()
	}
}

