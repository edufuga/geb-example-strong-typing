package methods

import basic.TypedPage
import geb.Browser
import geb.Module
import geb.Page
import geb.navigator.Navigator
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import groovy.transform.stc.ClosureParams
import module.DictionaryCategory
import module.ModuleWithElementMethods

@TypeChecked
class PageWithElementMethods extends TypedPage {

	@Override
	public String getPageUrl() {
		return "http://de.pons.com/";
	}
	
	/**
	 * Final parameterless Closure.
	 */
	private final Closure<Navigator> _searchBox = {$("input#q")}

	/**
	 * Search box.
	 * 
	 * <p>
	 * The Navigator (search box) comes from a final parameterless
	 * Closure that is cached and waited for.
	 * 
	 * <p>
	 * The caching just works when the closure {@link _searchBox}
	 * is final. It would for example not work if the code of the
	 * Closure, i.e. <code>{$("input#q")}</code>, was written
	 * directly as an argument of the {@link #getDefinition} method,
	 * i.e. <code>getDefinition({$("input#q")}, true, true)</code>.
	 * The reason is: Every time the method is called, a new Closure
	 * is created; although it has the same code, its hashcode is
	 * different at every method call.
	 * 
	 * @return Search box.
	 */
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
	 * Parameterized Closure.
	 * 
	 * <p>
	 * Memoized for caching closure calls with its parameter and return value.
	 */
	private final Closure<Navigator> _menu = {int idx -> bar.$("li", idx)}.memoize()

	/**
	 * Parameterized Closure.
	 * 
	 * <p>
	 * A call to the Closure {@link #_menu} with the parameter is
	 * written inside of <em>another</em> Closure; that Closure is
	 * parameterless.
	 * 
	 * <p>
	 * The parameterless Closure is passed to {@link #getDefinition}
	 * for enabling caching
	 * (the Closure {@link _menu} has to be <strong>final</strong>)
	 * and waiting.
	 * 
	 * <p>
	 * The method {@link #getDefinition} is used as a method reference
	 * in order to define the resulting Closure.
	 */
	final Closure<Navigator> waitedMenu = {int idx -> this.&getDefinition({_menu(idx)}, true, true)}

	/**
	 * Parameterized method.
	 * 
	 * <p>
	 * A call to the Closure {@link #_menu} with the parameter is
	 * written inside of <em>another</em> Closure; that Closure is
	 * parameterless.
	 * 
	 * @param idx Index
	 * @return Menu at index
	 */
	Navigator menu(int idx) {
		getDefinition({_menu(idx)}, true, true)
	}

	/**
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
		return getDefinition({$("#typeahead-menu").$("li")}, true).collect { Navigator n -> n.text() }
	}

	// This can't work because the method call "module" can't use a
	// not initialized "navigableSupport". The Page has to be initialized first.
	// 
	// ModuleWithElementMethods categoryModule = module(ModuleWithElementMethods)

	/**
	 * Encloses the parameterless module definition within a Closure.
	 * 
	 * <p>
	 * The module is available once the Closure is called.
	 */
	private final Closure<ModuleWithElementMethods> categoryModuleClosure = {
		module(ModuleWithElementMethods)
	}

	ModuleWithElementMethods getCategoryModule() {
		categoryModuleClosure.call()
	}

	/**
	 * Delegates to module. See {@link ModuleWithElementMethods#category(DictionaryCategory)}.
	 */
	Navigator category(DictionaryCategory cat) {
		categoryModule.category(cat)
	}

	/**
	 * Encloses the parameterized module definition within a Closure.
	 * 
	 * <p>
	 * This represents the category "dictionary". It is a concrete element of "category".
	 */
	private final Closure<ModuleWithElementMethods> dictionaryModuleClosure = {
		module(new ModuleWithElementMethods(DictionaryCategory.DICTIONARY))
	}

	ModuleWithElementMethods getDictionaryModule() {
		dictionaryModuleClosure.call()
	}

	Navigator getDictionary() {
		dictionaryModule.category.call()
	}
}
