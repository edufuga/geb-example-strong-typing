package localization

import java.util.List

import basic.TypedPage
import groovy.transform.TypeChecked

@TypeChecked
class HomePageGerman extends HomePage<HomePageGermanLocalizer> {

	static url = "http://de.pons.com/"

	@Override
	public HomePageGermanLocalizer getLocalizer() {
		return new HomePageGermanLocalizer();
	}
}
