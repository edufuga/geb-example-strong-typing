package localization

import java.util.List

import actions.Action
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

	public <O extends TypedPage> O run(Action<HomePageSpanish, O> action) {
		println "Running action '$action' with page '$this'."
		return action.run(this);
	}

}
