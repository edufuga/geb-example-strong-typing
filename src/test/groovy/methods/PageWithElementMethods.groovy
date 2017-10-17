package methods

import basic.TypedPage
import geb.Browser
import geb.Page
import geb.navigator.Navigator
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode

@TypeChecked
class PageWithElementMethods extends TypedPage {

	static url = "http://de.pons.com/"

	private final Closure<Navigator> _searchBox = {$("input#q")}

	Navigator getSearchBox() {
		getDefinition(_searchBox, true, true)
	}

	Navigator getWrapper() {
		$("div.search-large-wrapper")
	}

	Navigator getBar() {
		$(".nav-secondary")
	}

	private final Closure<Navigator> _menu = {int idx -> bar.$("li", idx)}

	final Closure<Navigator> waitedMenu = {int idx -> this.&getDefinition({_menu(idx)}, true, true)}

	Navigator menu(int idx) {
		getDefinition({_menu(idx)}, true, true)
	}

	/*
	 * Searches the given word.
	 */
	void search(String word) {
		println "Search word '$word'."
		searchBox.value(word)
		println "Results: "
		words?.each {
			println it
		}
	}

	/*
	 * Returns the list of words matched by the dictionary.
	 */
	List<String> getWords() {
		return getDefinition({$("#typeahead-menu").$("li")}, true).collect { Navigator n -> n.text() }
	}

}
