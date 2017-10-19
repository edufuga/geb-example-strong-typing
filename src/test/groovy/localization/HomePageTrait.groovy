package localization

import java.util.List

import geb.navigator.Navigator
import groovy.transform.TypeChecked

// FIXME: Be able to use $ method.
// @TypeChecked
trait HomePageTrait<L extends HomePageLocalizer> {

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

	Navigator getSearchBox() {
		$("#q")
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
	public void lookUp(String word) {
		println "Search word '$word'."
		searchBox.value(word)
		println "Results: "
		words?.each {
			println it
		}
	}

}
