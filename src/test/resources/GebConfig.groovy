import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

//baseUrl = "http://de.pons.com/" // Deutsch

//def c = { new ChromeDriver() }
//
//def f = { new FirefoxDriver() }
//
//driver = {
//	def driverInstance = f()
//	driverInstance.manage().window().maximize()
//	driverInstance
//}

environments {	
	chrome {
		driver = { new ChromeDriver() }
	}
	
	firefox {
		driver = { new FirefoxDriver() }
	}
}

baseNavigatorWaiting = true

waiting { 
	timeout = 10
	retryInterval = 0.5
	atCheckWaiting = true	// This doesn't work with new Firefox and new Gecko driver.
}
