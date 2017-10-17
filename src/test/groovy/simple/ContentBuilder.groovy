package simple
import geb.Browser
import geb.Page
import geb.content.PageContentTemplate
import groovy.transform.TypeChecked

// FIXME: The order of the builder is not explicit.
// Do something like Browser -> Page (-> Cache/Wait) -> Definition -> Build
@TypeChecked
class ContentBuilder {

	Browser browser
	Page page
	boolean cache
	Closure definition

	static ContentBuilder newInstance() {
		return new ContentBuilder()
	}
	
	ContentBuilder withPage(Page page) {
		this.page = page
		return this
	}

	ContentBuilder withBrowser(Browser browser) {
		this.browser = browser
		return this
	}
	
	ContentBuilder withCache(boolean cache) {
		this.cache = cache
		return this
	}

	ContentBuilder withDefinition(Closure definition) {
		this.definition = definition
		return this
	}

	PageContentTemplate build() {
		Objects.requireNonNull(browser, "The browser has to exist")
		Objects.requireNonNull(page, "The page has to exist")
		Objects.requireNonNull(definition, "The content definition has to exist")
		return new PageContentTemplate(browser, null, "someName", [cache:cache], definition , browser.navigatorFactory)
	}

}
