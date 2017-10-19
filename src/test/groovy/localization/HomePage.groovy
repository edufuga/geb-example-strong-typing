package localization

import basic.TypedPage
import geb.navigator.Navigator

abstract class HomePage<L extends HomePageLocalizer> extends TypedPage
	implements HomePageTrait<L> {

}
