package localization

import java.util.List

import basic.TypedPage
import geb.navigator.Navigator
import groovy.lang.MetaClass

class HomePageSpanish extends HomePage<HomePageSpanishLocalizer> {

	static url = "http://es.pons.com/"

	@Override
	public HomePageSpanishLocalizer getLocalizer() {
		return new HomePageSpanishLocalizer();
	}

}
