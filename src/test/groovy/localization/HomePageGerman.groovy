package localization

import java.util.List

import basic.TypedPage

class HomePageGerman extends HomePage<HomePageGermanLocalizer> {

	static url = "http://de.pons.com/"

	@Override
	public HomePageGermanLocalizer getLocalizer() {
		return new HomePageGermanLocalizer();
	}
}
