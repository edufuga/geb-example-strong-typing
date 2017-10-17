package methods

import basic.TypedPage
import geb.Browser
import geb.Page
import geb.navigator.Navigator
import groovy.transform.TypeChecked

@TypeChecked
class PageWithElementMethods extends TypedPage {

	static url = "http://de.pons.com/"

	// This should go to TypedPage.
	Navigator getDefinition(Closure<Navigator> definition, boolean waiting = false) {
		if (waiting) {
			return waitFor(definition)
		}
		return definition()
	}

	Navigator getSearchBox() {
		$("input#q")
	}

	Navigator waitForSearchBox() {
		getDefinition(this.&getSearchBox, true)
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
