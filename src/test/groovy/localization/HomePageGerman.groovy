package localization

import java.util.List

import basic.TypedPage

class HomePageGerman extends TypedPage implements HomePageTrait<HomePageGermanLocalizer> {

	static url = "http://de.pons.com/"

	@Override
	public HomePageGermanLocalizer getLocalizer() {
		return new HomePageGermanLocalizer();
	}
}
