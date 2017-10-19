package localization

import groovy.transform.TypeChecked

@TypeChecked
class HomePageSpanishLocalizer implements HomePageLocalizer {

	@Override
	public String getMainTitle() {
		"Diccionario en línea PONS"
	}

	@Override
	public String getSubTitle() {
		"Basta una consulta para traducir correctamente"
	}

}
