package localization

import java.util.List

import basic.TypedPage
import geb.navigator.Navigator
import groovy.lang.MetaClass
import groovy.transform.TypeChecked

@TypeChecked
class HomePageSpanish extends HomePage<HomePageSpanishLocalizer> {

	@Override
	public String getPageUrl() {
		return "http://es.pons.com/";
	}

	@Override
	public HomePageSpanishLocalizer getLocalizer() {
		return new HomePageSpanishLocalizer();
	}

}
