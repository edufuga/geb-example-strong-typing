package localization

import actions.Action
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

@CompileStatic
class HomePageGermanToSpanishGroovyAction implements Action<HomePageGerman, HomePageSpanish> {

	@Override
	public HomePageSpanish run(HomePageGerman from) {
		from.browser.<HomePageSpanish> to(HomePageSpanish)
	}

}
