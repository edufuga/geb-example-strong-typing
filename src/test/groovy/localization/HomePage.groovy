package localization

import java.util.List

import basic.TypedPage
import geb.navigator.Navigator
import groovy.lang.Closure

abstract class HomePage<L extends HomePageLocalizer> extends TypedPage {

	abstract L getLocalizer()

	Navigator getMainTitle() {
		$("div.search-large-wrapper").$("h1")
	}

	boolean checkTitle() {
		mainTitle.text() == localizer.getMainTitle()
	}

	Navigator getSubTitle() {
		$("div.search-large-wrapper").$("h2")
	}

	boolean checkSubTitle() {
		subTitle.text() == localizer.getSubTitle()
	}

	private final Closure<Navigator> _searchBox = {$("#q")}

	Navigator getSearchBox() {
		getDefinition(_searchBox, true, true)
	}

	Navigator getWrapper() {
		$("div.search-large-wrapper")
	}

	Navigator getBar() {
		$(".nav-secondary")
	}

	/**
	 * Returns the list of words matched by the dictionary.
	 *
	 * <p>
	 * The spread dot operator can't be used with
	 * <code>@TypeChecked</code>; <code>collect</code>
	 * is used instead with a typed parameter.
	 *
	 * @return List of words.
	 */
	List<String> getWords() {
		return $("#typeahead-menu").$("li").collect { Navigator n -> n.text() }
	}

	/**
	 * Searches the given word.
	 */
	public void search(String word) {
		println "Search word '$word'."
		searchBox.value(word)
		println "Results: "
		words?.each {
			println it
		}
	}

}
