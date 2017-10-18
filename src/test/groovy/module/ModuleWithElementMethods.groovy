package module

import basic.TypedModule
import geb.navigator.Navigator
import groovy.transform.TypeChecked

@TypeChecked
class ModuleWithElementMethods extends TypedModule {

	/**
	 * Different "categories" of the main bar of the online dictionary.
	 * 
	 * <p>
	 * Parameterized Closure with the dictionary category type.
	 * The dictionary category is an enumeration for more type safety.
	 */
	Closure<Navigator> category = {DictionaryCategory category ->
		$("ul.nav-primary").$(["data-pons-tab": category.category] as Map, "li")
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
