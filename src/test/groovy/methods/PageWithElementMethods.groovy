package methods

import basic.TypedPage
import geb.Browser
import geb.Page
import geb.navigator.Navigator
import groovy.transform.TypeChecked

@TypeChecked
class PageWithElementMethods extends TypedPage {

	static url = "http://de.pons.com/"

	Navigator getSearch() {
		$("input#q")
	}

	Navigator getWrapper() {
		$("div.search-large-wrapper")
	}

}
