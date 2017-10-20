package localization

import actions.Action
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

@CompileStatic
class HomePageGermanToSpanishGroovyAction implements Action<HomePageGerman, HomePageSpanish> {

	@Override
	public HomePageSpanish run(HomePageGerman from) {
		Objects.requireNonNull(from, "The page has to exist.")
		from.browser.<HomePageSpanish> to(HomePageSpanish)
	}

}
