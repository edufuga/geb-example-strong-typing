package localization

import java.util.List

import basic.TypedPage
import geb.navigator.Navigator
import groovy.lang.MetaClass

class HomePageSpanish extends TypedPage implements HomePageTrait<HomePageSpanishLocalizer> {

	static url = "http://es.pons.com/"

	@Override
	public HomePageSpanishLocalizer getLocalizer() {
		return new HomePageSpanishLocalizer();
	}

}
