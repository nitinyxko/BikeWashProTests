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
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

/**
 * TC05: Book Basic Wash Service
 * Description: Verify customer can book a basic wash service (₹150, 30 minutes)
 * Priority: Critical
 * Tags: Customer, ServiceBooking, Critical
 */

try {
	// Step 1: Login as customer using reusable keyword
	KeywordUtil.logInfo('Logging in as customer')
	CustomKeywords.'keywords.LoginHelper.loginAsCustomer'(GlobalVariable.testPhoneNumber, GlobalVariable.testOTP)
	Mobile.delay(2)
	
	// Step 2: Verify Customer Home screen
	KeywordUtil.logInfo('Verifying Customer Home screen loaded')
	Mobile.verifyElementVisible(findTestObject('Object Repository/CustomerHomeScreen/lblWelcome'), 10)
	Mobile.verifyElementVisible(findTestObject('Object Repository/CustomerHomeScreen/btnBookService'), 10)
	
	// Step 3: Tap Book Service button
	KeywordUtil.logInfo('Tapping Book Service button')
	Mobile.tap(findTestObject('Object Repository/CustomerHomeScreen/btnBookService'), 10)
	Mobile.delay(2)
	
	// Step 4: Wait for Service Booking screen to load
	Mobile.waitForElementPresent(findTestObject('Object Repository/ServiceBookingScreen/lblSelectService'), 15)
	Mobile.verifyElementVisible(findTestObject('Object Repository/ServiceBookingScreen/lblSelectService'), 10)
	
	// Step 5: Verify all service options are visible
	Mobile.verifyElementVisible(findTestObject('Object Repository/ServiceBookingScreen/cardBasicWash'), 10)
	Mobile.verifyElementVisible(findTestObject('Object Repository/ServiceBookingScreen/cardPremiumWash'), 10)
	Mobile.verifyElementVisible(findTestObject('Object Repository/ServiceBookingScreen/cardDeluxeWash'), 10)
	
	// Step 6: Select Basic Wash service
	KeywordUtil.logInfo('Selecting Basic Wash service (₹150)')
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/cardBasicWash'), 10)
	Mobile.delay(1)
	
	// Step 7: Verify Basic Wash is selected
	Mobile.verifyElementChecked(findTestObject('Object Repository/ServiceBookingScreen/cardBasicWash'), 5)
	
	// Step 8: Verify price is displayed correctly
	String priceText = Mobile.getText(findTestObject('Object Repository/ServiceBookingScreen/lblPrice'), 5)
	assert priceText.contains('₹150') : 'Price does not match Basic Wash price'
	KeywordUtil.logInfo('Price verified: ' + priceText)
	
	// Step 9: Verify duration is displayed
	String durationText = Mobile.getText(findTestObject('Object Repository/ServiceBookingScreen/lblDuration'), 5)
	assert durationText.contains('30') : 'Duration does not match Basic Wash duration'
	
	// Step 10: Scroll down to bike details section
	Mobile.scrollToText('Bike Details', FailureHandling.OPTIONAL)
	Mobile.delay(1)
	
	// Step 11: Enter bike model
	KeywordUtil.logInfo('Entering bike details')
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/txtBikeModel'), 5)
	Mobile.setText(findTestObject('Object Repository/ServiceBookingScreen/txtBikeModel'), 'Honda CB350', 10)
	
	// Step 12: Enter bike registration number
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/txtBikeNumber'), 5)
	Mobile.setText(findTestObject('Object Repository/ServiceBookingScreen/txtBikeNumber'), 'TN01AB1234', 10)
	
	Mobile.hideKeyboard()
	Mobile.delay(1)
	
	// Step 13: Select location
	KeywordUtil.logInfo('Selecting location')
	Mobile.scrollToText('Location', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/btnUseCurrentLocation'), 10)
	Mobile.delay(2)
	
	// Allow location permission if prompted
	if (Mobile.verifyElementExist(findTestObject('Object Repository/Permissions/btnAllowLocation'), 5, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('Object Repository/Permissions/btnAllowLocation'), 5)
		Mobile.delay(1)
	}
	
	// Step 14: Select time slot
	KeywordUtil.logInfo('Selecting time slot')
	Mobile.scrollToText('Select Time', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/timeSlot_14_00'), 10)
	Mobile.delay(1)
	
	// Step 15: Add special instructions
	Mobile.scrollToText('Special Instructions', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/txtSpecialInstructions'), 5)
	Mobile.setText(findTestObject('Object Repository/ServiceBookingScreen/txtSpecialInstructions'),
				   'Please call before arriving. Park near main gate.', 10)
	Mobile.hideKeyboard()
	Mobile.delay(1)
	
	// Step 16: Review booking summary
	Mobile.scrollToText('Booking Summary', FailureHandling.OPTIONAL)
	Mobile.verifyElementVisible(findTestObject('Object Repository/ServiceBookingScreen/lblBookingSummary'), 10)
	
	// Step 17: Tap Continue to Payment
	KeywordUtil.logInfo('Proceeding to payment')
	Mobile.tap(findTestObject('Object Repository/ServiceBookingScreen/btnContinuePayment'), 10)
	Mobile.delay(2)
	
	// Step 18: Wait for Payment screen
	Mobile.waitForElementPresent(findTestObject('Object Repository/PaymentScreen/lblSelectPayment'), 15)
	
	// Step 19: Verify payment options are visible
	Mobile.verifyElementVisible(findTestObject('Object Repository/PaymentScreen/optionCOD'), 10)
	Mobile.verifyElementVisible(findTestObject('Object Repository/PaymentScreen/optionOnline'), 10)
	Mobile.verifyElementVisible(findTestObject('Object Repository/PaymentScreen/optionUPI'), 10)
	
	// Step 20: Select Cash on Delivery payment method
	KeywordUtil.logInfo('Selecting COD payment method')
	Mobile.tap(findTestObject('Object Repository/PaymentScreen/optionCOD'), 10)
	Mobile.delay(1)
	
	// Step 21: Verify COD is selected
	Mobile.verifyElementChecked(findTestObject('Object Repository/PaymentScreen/optionCOD'), 5)
	
	// Step 22: Verify final amount
	String finalAmount = Mobile.getText(findTestObject('Object Repository/PaymentScreen/lblTotalAmount'), 5)
	assert finalAmount.contains('₹150') : 'Total amount does not match'
	KeywordUtil.logInfo('Final amount verified: ' + finalAmount)
	
	// Step 23: Tap Confirm Booking button
	KeywordUtil.logInfo('Confirming booking')
	Mobile.tap(findTestObject('Object Repository/PaymentScreen/btnConfirmBooking'), 10)
	Mobile.delay(3)
	
	// Step 24: Wait for confirmation screen
	Mobile.waitForElementPresent(findTestObject('Object Repository/ConfirmationScreen/lblBookingConfirmed'), 20)
	
	// Step 25: Verify booking confirmation
	Mobile.verifyElementVisible(findTestObject('Object Repository/ConfirmationScreen/lblBookingConfirmed'), 10)
	Mobile.verifyElementVisible(findTestObject('Object Repository/ConfirmationScreen/iconSuccess'), 10)
	
	// Step 26: Capture and verify booking ID
	String bookingID = Mobile.getText(findTestObject('Object Repository/ConfirmationScreen/lblBookingID'), 10)
	assert bookingID != null && bookingID.length() > 0 : 'Booking ID not generated'
	KeywordUtil.logInfo('Booking created successfully with ID: ' + bookingID)
	GlobalVariable.currentBookingID = bookingID
	
	// Step 27: Verify booking details
	Mobile.verifyElementText(findTestObject('Object Repository/ConfirmationScreen/lblServiceType'), 'Basic Wash', FailureHandling.CONTINUE_ON_FAILURE)
	Mobile.verifyElementText(findTestObject('Object Repository/ConfirmationScreen/lblAmount'), '₹150', FailureHandling.CONTINUE_ON_FAILURE)
	
	// Step 28: Take screenshot of confirmation
	Mobile.takeScreenshot('Screenshots/BasicWash_Booking_Confirmation.png')
	
	KeywordUtil.markPassed('Basic Wash service booked successfully. Booking ID: ' + bookingID)
	
} catch (Exception e) {
	KeywordUtil.markFailed('Basic Wash booking failed: ' + e.getMessage())
	Mobile.takeScreenshot('Screenshots/BasicWash_Booking_Failed.png')
	throw e
} finally {
	Mobile.closeApplication()
}

