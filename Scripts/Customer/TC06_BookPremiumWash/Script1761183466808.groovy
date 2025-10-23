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
 * TC06: Book Premium Wash Service
 * Description: Verify customer can book premium wash service (₹250, 45 minutes)
 * Priority: High
 * Tags: Customer, ServiceBooking
 */

try {
	KeywordUtil.logInfo('Starting Premium Wash booking test')
	
	// Login and navigate to booking
	CustomKeywords.'keywords.LoginHelper.loginAsCustomer'(GlobalVariable.testPhoneNumber, GlobalVariable.testOTP)
	Mobile.delay(2)
	
	Mobile.tap(findTestObject('Object Repository/CustomerHomeScreen/btnBookService'), 10)
	Mobile.delay(2)
	
	// Select Premium Wash
	KeywordUtil.logInfo('Selecting Premium Wash service (₹250)')
	Mobile.waitForElementPresent(findTestObject('Object Repository/ServiceBookingScreen/cardPremiumWash'), 15)
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/cardPremiumWash'), 10)
	Mobile.delay(1)
	
	// Verify price
	String priceText = Mobile.getText(findTestObject('Object Repository/ServiceBookingScreen/lblPrice'), 5)
	assert priceText.contains('₹250') : 'Price does not match Premium Wash price'
	
	// Fill booking details
	Mobile.scrollToText('Bike Details', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/txtBikeModel'), 5)
	Mobile.setText(findTestObject('Object Repository/ServiceBookingScreen/txtBikeModel'), 'Royal Enfield Classic 350', 10)
	
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/txtBikeNumber'), 5)
	Mobile.setText(findTestObject('Object Repository/ServiceBookingScreen/txtBikeNumber'), 'TN09XY5678', 10)
	Mobile.hideKeyboard()
	
	// Select location and time
	Mobile.scrollToText('Location', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/btnUseCurrentLocation'), 10)
	Mobile.delay(2)
	
	Mobile.scrollToText('Select Time', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/timeSlot_15_00'), 10)
	Mobile.delay(1)
	
	// Proceed to payment
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/btnContinuePayment'), 10)
	Mobile.delay(2)
	
	// Select online payment
	Mobile.waitForElementPresent(findTestObject('Object Repository/PaymentScreen/optionOnline'), 15)
	Mobile.tap(findTestObject('Object Repository/PaymentScreen/optionOnline'), 10)
	Mobile.delay(1)
	
	// Confirm booking
	Mobile.tap(findTestObject('Object Repository/PaymentScreen/btnConfirmBooking'), 10)
	Mobile.delay(3)
	
	// Verify confirmation
	Mobile.waitForElementPresent(findTestObject('Object Repository/ConfirmationScreen/lblBookingConfirmed'), 20)
	String bookingID = Mobile.getText(findTestObject('Object Repository/ConfirmationScreen/lblBookingID'), 10)
	KeywordUtil.logInfo('Premium Wash booking created: ' + bookingID)
	
	KeywordUtil.markPassed('Premium Wash service booked successfully')
	
} catch (Exception e) {
	KeywordUtil.markFailed('Premium Wash booking failed: ' + e.getMessage())
	Mobile.takeScreenshot()
	throw e
} finally {
	Mobile.closeApplication()
}
