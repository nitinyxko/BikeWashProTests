import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

/**
 * TC08: Track Order
 * Description: Verify customer can track their order in real-time
 * Priority: High
 * Tags: Customer, OrderTracking
 */

try {
	KeywordUtil.logInfo('Starting Order Tracking test')
	
	// Login as customer
	CustomKeywords.'keywords.LoginHelper.loginAsCustomer'(GlobalVariable.testPhoneNumber, GlobalVariable.testOTP)
	Mobile.delay(2)
	
	// Navigate to My Orders
	KeywordUtil.logInfo('Navigating to My Orders')
	Mobile.tap(findTestObject('Object Repository/CustomerHomeScreen/tabMyOrders'), 10)
	Mobile.delay(2)
	
	// Tap on first active order
	Mobile.waitForElementPresent(findTestObject('Object Repository/OrdersScreen/firstActiveOrder'), 15)
	Mobile.tap(findTestObject('Object Repository/OrdersScreen/firstActiveOrder'), 10)
	Mobile.delay(2)
	
	// Verify Order Tracking screen
	Mobile.waitForElementPresent(findTestObject('Object Repository/OrderTrackingScreen/lblOrderStatus'), 15)
	Mobile.verifyElementVisible(findTestObject('Object Repository/OrderTrackingScreen/mapView'), 10)
	
	// Verify order status
	String orderStatus = Mobile.getText(findTestObject('Object Repository/OrderTrackingScreen/lblOrderStatus'), 5)
	KeywordUtil.logInfo('Current order status: ' + orderStatus)
	
	// Verify washer information is visible
	Mobile.verifyElementVisible(findTestObject('Object Repository/OrderTrackingScreen/lblWasherName'), 10)
	Mobile.verifyElementVisible(findTestObject('Object Repository/OrderTrackingScreen/lblWasherPhone'), 10)
	Mobile.verifyElementVisible(findTestObject('Object Repository/OrderTrackingScreen/imgWasherPhoto'), 10)
	
	// Verify tracking timeline
	Mobile.scrollToText('Order Timeline', FailureHandling.OPTIONAL)
	Mobile.verifyElementVisible(findTestObject('Object Repository/OrderTrackingScreen/timelineOrderPlaced'), 10)
	
	// Test call washer button
	Mobile.verifyElementVisible(findTestObject('Object Repository/OrderTrackingScreen/btnCallWasher'), 10)
	
	// Test message washer button
	Mobile.verifyElementVisible(findTestObject('Object Repository/OrderTrackingScreen/btnMessageWasher'), 10)
	
	Mobile.takeScreenshot('Screenshots/Order_Tracking.png')
	
	KeywordUtil.markPassed('Order tracking verified successfully')
	
} catch (Exception e) {
	KeywordUtil.markFailed('Order tracking failed: ' + e.getMessage())
	Mobile.takeScreenshot()
	throw e
} finally {
	Mobile.closeApplication()
}
