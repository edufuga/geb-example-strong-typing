package localization

import groovy.transform.TypeChecked

@TypeChecked
class HomePageGermanLocalizer implements HomePageLocalizer {

	@Override
	public String getMainTitle() {
		"PONS Online-Wörterbuch"
	}

	@Override
	public String getSubTitle() {
		"Einfach nachschlagen und richtig übersetzen"
	}

}
