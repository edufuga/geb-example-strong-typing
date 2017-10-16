import geb.Browser
import geb.Page

import geb.content.PageContentTemplate
import groovy.transform.InheritConstructors
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode

@TypeChecked
class SimpleElements extends Elements {

	public SimpleElements(Browser browser, Page page) {
		super(browser, page)
	}

	PageContentTemplate search
	PageContentTemplate wrapper

	@TypeChecked(TypeCheckingMode.SKIP)
	void initialize() {
		search = builder.withDefinition({$("input#q")}).build()
		wrapper = builder.withDefinition({$("div.search-large-wrapper")}).build()
	}

}
