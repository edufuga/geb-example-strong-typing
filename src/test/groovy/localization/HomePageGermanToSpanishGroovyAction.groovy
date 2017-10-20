package localization

import java.util.Objects

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

@CompileStatic
class HomePageGermanToSpanishGroovyAction implements HomePageGermanAction<HomePageSpanish> {

	@Override
	public HomePageSpanish run(HomePageGerman from) {
		Objects.requireNonNull(from, "The page has to exist.")
		Objects.requireNonNull(from.browser, "The browser has to exist.")
		from.browser.<HomePageSpanish> to(HomePageSpanish)
	}

}
