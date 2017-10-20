package localization;

import actions.Action;
import basic.TypedPage;
import groovy.lang.Closure
import groovy.transform.TypeChecked

@TypeChecked
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
		println "Running action '$action' with page '$this'."
		return action.run(this);
	}
}
