package methods

import basic.TypedPage
import geb.Browser
import geb.Page
import geb.navigator.Navigator
import groovy.transform.TypeChecked

@TypeChecked
class PageWithElementMethods extends TypedPage {

	static url = "http://de.pons.com/"

	Navigator getSearchBox() {
		$("input#q")
	}

	Navigator waitForSearchBox() {
		waitFor(this.&getSearchBox)
	}

	Navigator getWrapper() {
		$("div.search-large-wrapper")
	}

	/*
	 * Searches the given word.
	 */
	void search(String word) {
		searchBox.value(word)
	}

}
