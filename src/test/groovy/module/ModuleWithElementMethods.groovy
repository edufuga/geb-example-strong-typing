package module

import basic.TypedModule
import geb.navigator.Navigator
import groovy.transform.TypeChecked

@TypeChecked
class ModuleWithElementMethods extends TypedModule {

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
