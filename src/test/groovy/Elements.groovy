import geb.Browser
import geb.Page
import geb.content.PageContentTemplate
import groovy.transform.TypeChecked
import geb.navigator.Navigator

//@TypeChecked
class Elements {
	Page page
	Browser browser

	Elements(Browser browser, Page page) {
		this.browser = browser
		this.page = page
	}

	PageContentTemplate search = ContentBuilder.newInstance().withPage(page).withBrowser(browser).withDefinition({$("input#q")}).build()
	PageContentTemplate wrapper = ContentBuilder.newInstance().withPage(page).withBrowser(browser).withDefinition({$("div.search-large-wrapper")}).build()
}
