import geb.Browser
import geb.Page
import geb.content.PageContentTemplate
import groovy.transform.TypeChecked
import geb.navigator.Navigator

@TypeChecked
abstract class Elements {
	protected Page page
	protected Browser browser
	protected ContentBuilder builder

	Elements(Browser browser, Page page) {
		this.browser = browser
		this.page = page
		this.builder = ContentBuilder.newInstance()
			.withBrowser(browser)
			.withPage(page)
		initialize()
	}

	abstract void initialize()
}
