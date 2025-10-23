import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable

/**
 * TC05: Book Basic Wash Service (₹150)
 * Tests complete booking flow matching React Native app
 */

try {
	KeywordUtil.logInfo('=== TC05: Book Basic Wash Started ===')
	
	// Step 1: Login as customer
	CustomKeywords.'keywords.LoginHelper.loginAsCustomer'(GlobalVariable.testPhoneCustomer, GlobalVariable.testOTP)
	Mobile.delay(2)
	
	// Step 2: Verify Customer Home Screen
	Mobile.verifyElementVisible(findTestObject('CustomerHome/txt_Greeting'), 10)
	Mobile.verifyElementVisible(findTestObject('CustomerHome/txt_UserName'), 10)
	
	// Step 3: Tap "Book Service" quick action
	Mobile.tap(findTestObject('CustomerHome/btn_BookService'), 10)
	Mobile.delay(2)
	
	// Step 4: Select Basic Wash service (₹150, 30 min)
	Mobile.waitForElementPresent(findTestObject('ServiceBooking/txt_SelectService'), 15)
	Mobile.tap(findTestObject('ServiceBooking/card_BasicWash'), 10)
	Mobile.delay(1)
	
	// Step 5: Verify price is ₹150
	String priceText = Mobile.getText(findTestObject('ServiceBooking/txt_Price'), 5)
	assert priceText.contains('150') : 'Price mismatch for Basic Wash'
	
	// Step 6: Fill service location
	Mobile.scrollToText('Service Location', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('ServiceBooking/input_Address'), 5)
	Mobile.setText(findTestObject('ServiceBooking/input_Address'), '123, MG Road, Bangalore', 10)
	
	// Step 7: Fill bike number
	Mobile.scrollToText('Bike Details', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('ServiceBooking/input_BikeNumber'), 5)
	Mobile.setText(findTestObject('ServiceBooking/input_BikeNumber'), 'KA01AB1234', 10)
	Mobile.hideKeyboard()
	
	// Step 8: Select date (today)
	Mobile.scrollToText('Select Date', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('ServiceBooking/btn_SelectDate'), 5)
	Mobile.delay(1)
	Mobile.tap(findTestObject('ServiceBooking/btn_ConfirmDate'), 5)
	
	// Step 9: Select time slot (10:00 AM)
	Mobile.scrollToText('Select Time', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('ServiceBooking/slot_1000'), 10)
	Mobile.delay(1)
	
	// Step 10: Add special instructions
	Mobile.scrollToText('Special Instructions', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('ServiceBooking/input_SpecialInstructions'), 5)
	Mobile.setText(findTestObject('ServiceBooking/input_SpecialInstructions'),
				   'Please call before arriving', 10)
	Mobile.hideKeyboard()
	
	// Step 11: Select payment method (COD)
	Mobile.scrollToText('Payment Method', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('ServiceBooking/radio_PaymentCOD'), 10)
	Mobile.delay(1)
	
	// Step 12: Verify booking summary
	Mobile.scrollToText('Booking Summary', FailureHandling.OPTIONAL)
	Mobile.verifyElementVisible(findTestObject('ServiceBooking/txt_BookingSummary'), 5)
	
	// Verify service price: ₹150
	// Verify platform fee: ₹15 (10%)
	// Verify total: ₹165
	String summaryText = Mobile.getText(findTestObject('ServiceBooking/txt_BookingSummary'), 5)
	assert summaryText.contains('150') : 'Service price not found in summary'
	assert summaryText.contains('165') : 'Total amount incorrect'
	
	// Step 13: Tap Book Now button
	Mobile.scrollToText('Book Now', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('ServiceBooking/btn_BookNow'), 10)
	Mobile.delay(3)
	
	// Step 14: Handle confirmation dialog
	if (Mobile.verifyElementExist(findTestObject('ServiceBooking/dialog_Confirm'), 5, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('ServiceBooking/btn_ConfirmBooking'), 5)
		Mobile.delay(2)
	}
	
	// Step 15: Verify booking success
	Mobile.waitForElementPresent(findTestObject('ServiceBooking/txt_BookingConfirmed'), 20)
	String bookingID = Mobile.getText(findTestObject('ServiceBooking/txt_BookingID'), 5)
	GlobalVariable.lastBookingID = bookingID
	
	KeywordUtil.markPassed("✅ Basic Wash booked successfully. Booking ID: ${bookingID}")
	Mobile.takeScreenshot('Screenshots/TC05_Success.png')
	
} catch (Exception e) {
	KeywordUtil.markFailed("❌ Basic Wash booking failed: ${e.getMessage()}")
	Mobile.takeScreenshot('Screenshots/TC05_Failed.png')
	throw e
} finally {
	Mobile.closeApplication()
}
