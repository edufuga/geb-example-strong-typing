package localization

import geb.spock.GebReportingSpec
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

class HomePageSpec extends GebReportingSpec {
	def "simple test"() {
		when:
		assert browser
		HomePageSpanish page = browser.to(HomePageSpanish)

		then:
		// page.searchBox.value("esfera")

		// The "search" method was renamed to "lookUp".
		// Somehow Eclipse marks the method as unknown (?)
		page.lookUp("esfera")
	}
}
