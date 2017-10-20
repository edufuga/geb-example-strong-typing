package localization;

import actions.Action;

public class HomePageGermanToSpanishJavaAction implements Action<HomePageGerman, HomePageSpanish> {

	@Override
	public HomePageSpanish run(HomePageGerman from) {
		return from.getBrowser().to(HomePageSpanish.class);
	}

}
