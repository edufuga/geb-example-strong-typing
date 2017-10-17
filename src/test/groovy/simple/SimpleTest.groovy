package simple
import geb.navigator.Navigator
import geb.spock.GebReportingSpec

class SimpleTest extends GebReportingSpec {

	def "simple test"() {
		when:
		assert browser
		SimplePage page = browser.to(SimplePage)

		then:
		// FIXME: The "get" method of "PageContentTemplate" is defined with "def".
		// It is too flexible (a content definition can return anything).
		// Limit it to a "Navigator".
		Navigator searchbox = page.search.get()
		searchbox.value("Test")

		// TODO: Do not allow calling locator methods via "navigatorFactory" of "PageContentTemplate".
		// Change "PageContentTemplate" for something else. The "name" is not necessary anymore.
		println page.wrapper.navigatorFactory.locator.$("h1").text()
	}
}
