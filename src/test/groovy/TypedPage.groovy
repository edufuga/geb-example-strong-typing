import geb.Browser
import geb.Page
import geb.content.NavigableSupport
import geb.download.DefaultDownloadSupport
import geb.download.DownloadSupport
import geb.frame.DefaultFrameSupport
import geb.frame.FrameSupport
import geb.interaction.DefaultInteractionsSupport
import geb.interaction.InteractionsSupport
import geb.js.AlertAndConfirmSupport
import geb.js.DefaultAlertAndConfirmSupport
import geb.url.UrlFragment
import geb.waiting.DefaultWaitingSupport
import geb.waiting.WaitingSupport
import groovy.transform.TypeChecked
import groovy.lang.Delegate
import groovy.transform.TypeCheckingMode

@TypeChecked
class TypedPage extends Page {

	protected Browser browser = super.getBrowser()
	protected NavigableSupport navigableSupport
	protected DownloadSupport downloadSupport
	protected WaitingSupport waitingSupport
	protected FrameSupport frameSupport
	protected InteractionsSupport interactionsSupport
	protected AlertAndConfirmSupport alertAndConfirmSupport

	protected ContentBuilder builder

	@TypeChecked(TypeCheckingMode.SKIP)
	@Override
	void to(Map params, UrlFragment fragment = null, Object[] args) {
		def path = convertToPath(*args)
		if (path == null) {
			path = ""
		}
		getInitializedBrowser().go(params, getPageUrl(path), fragment ?: getPageFragment())
		getInitializedBrowser().page(this)
	}
	
	@Override
	Browser getInitializedBrowser() {
		if (browser == null) {
			throw uninitializedException()
		}
		browser
	}

	@Override
	Page init(Browser browser) {
		this.browser = browser
		navigableSupport = new NavigableSupport(browser.navigatorFactory)
		downloadSupport = new DefaultDownloadSupport(browser)
		waitingSupport = new DefaultWaitingSupport(browser.config)
		frameSupport = new DefaultFrameSupport(browser)
		interactionsSupport = new DefaultInteractionsSupport(browser)
		alertAndConfirmSupport = new DefaultAlertAndConfirmSupport({ this.getJs() }, browser.config)
		builder = ContentBuilder.newInstance().withBrowser(browser).withPage(this)
		this
	}

	Elements elements

}
