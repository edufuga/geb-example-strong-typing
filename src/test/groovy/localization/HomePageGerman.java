package localization;

import actions.Action;
import basic.TypedPage;

class HomePageGerman extends HomePage<HomePageGermanLocalizer> {

	@Override
	public String getPageUrl() {
		return "http://de.pons.com/";
	}

	@Override
	public HomePageGermanLocalizer getLocalizer() {
		return new HomePageGermanLocalizer();
	}

	public <O extends TypedPage> O run(Action<HomePageGerman, O> action) {
		return action.run(this);
	}
}
