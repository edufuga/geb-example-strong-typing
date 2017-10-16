import geb.spock.GebReportingSpec

class SimpleTest extends GebReportingSpec {

	def "simple test"() {
		when:
		assert browser
		TestPage page = browser.to(TestPage)

		then:
		assert page.search
		println page.wrapper.navigatorFactory.locator.$("h1").text()
	}
}
