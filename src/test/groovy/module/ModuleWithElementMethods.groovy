package module

import basic.TypedModule
import geb.navigator.Navigator
import groovy.transform.TypeChecked

@TypeChecked
class ModuleWithElementMethods extends TypedModule {

	private DictionaryCategory categoria

	ModuleWithElementMethods(DictionaryCategory categoria) {
		// Objects.requireNonNull(categoria, "The dictionary category has to exist")
		this.categoria = categoria
	}

	/**
	 * Different "categories" of the main bar of the online dictionary.
	 * 
	 * <p>
	 * Parameterized Closure with the dictionary category type.
	 * The dictionary category is an enumeration for more type safety.
	 */
	Closure<Navigator> category = {DictionaryCategory category = categoria ->
		$("ul.nav-primary").$(["data-pons-tab": category.category] as Map, "li")
	}

	/**
	 * Delegates to {@link #category category} closure.
	 * 
	 * @param cat Category of interest
	 * @return Category navigator element
	 */
	Navigator category(DictionaryCategory cat = categoria) {
		category.call(cat)
	}
}

enum DictionaryCategory {
	DICTIONARY("dictionary"),
	SHOP("shop"),
	SELFSTUDY("self-study"),
	MYPONS("my-pons")

	final String category

	String category() {
		category
	}

	DictionaryCategory(String category) {
		this.category = category
	}
}
