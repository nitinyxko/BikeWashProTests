import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable

/**
 * TC01: Customer Registration Flow
 * Matches actual React Native app flow
 */

try {
	KeywordUtil.logInfo('=== TC01: Customer Registration Started ===')
	
	// Step 1: Launch app
	Mobile.startApplication(GlobalVariable.appPath, false)
	Mobile.delay(3)
	
	// Step 2: Tap "I'm a Customer" on Welcome screen
	Mobile.waitForElementPresent(findTestObject('Welcome/btn_Customer'), 20)
	Mobile.tap(findTestObject('Welcome/btn_Customer'), 10)
	Mobile.delay(2)
	
	// Step 3: Navigate to Register from Login screen
	Mobile.waitForElementPresent(findTestObject('Login/link_Register'), 15)
	Mobile.tap(findTestObject('Login/link_Register'), 10)
	Mobile.delay(2)
	
	// Step 4: Fill registration form
	KeywordUtil.logInfo('Filling registration form')
	
	// Full Name
	Mobile.tap(findTestObject('Register/input_FullName'), 5)
	Mobile.setText(findTestObject('Register/input_FullName'), 'John Doe Test', 10)
	
	// Phone Number (React Native Phone Input component)
	Mobile.tap(findTestObject('Register/input_PhoneNumber'), 5)
	Mobile.setText(findTestObject('Register/input_PhoneNumber'), '9876543210', 10)
	
	// Email
	Mobile.tap(findTestObject('Register/input_Email'), 5)
	Mobile.setText(findTestObject('Register/input_Email'), 'john.test@bikewash.com', 10)
	
	// Address
	Mobile.tap(findTestObject('Register/input_Address'), 5)
	Mobile.setText(findTestObject('Register/input_Address'), '123 MG Road', 10)
	
	// City
	Mobile.tap(findTestObject('Register/input_City'), 5)
	Mobile.setText(findTestObject('Register/input_City'), 'Bangalore', 10)
	
	// Pincode
	Mobile.tap(findTestObject('Register/input_Pincode'), 5)
	Mobile.setText(findTestObject('Register/input_Pincode'), '560001', 10)
	
	// Bike Number (Optional for customer)
	Mobile.scrollToText('Bike Number', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('Register/input_BikeNumber'), 5)
	Mobile.setText(findTestObject('Register/input_BikeNumber'), 'KA01AB1234', 10)
	
	// Hide keyboard
	Mobile.hideKeyboard()
	Mobile.delay(1)
	
	// Step 5: Accept Terms & Conditions
	Mobile.scrollToText('Terms & Conditions', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('Register/checkbox_Terms'), 5)
	
	// Step 6: Tap Register button
	Mobile.scrollToText('Create Account', FailureHandling.OPTIONAL)
	Mobile.tap(findTestObject('Register/btn_Register'), 10)
	Mobile.delay(3)
	
	// Step 7: Verify success (OTP screen or success message)
	Mobile.verifyElementVisible(findTestObject('Register/txt_RegistrationSuccess'), 10)
	
	KeywordUtil.markPassed('✅ Customer registration completed successfully')
	Mobile.takeScreenshot('Screenshots/TC01_Success.png')
	
} catch (Exception e) {
	KeywordUtil.markFailed("❌ Customer registration failed: ${e.getMessage()}")
	Mobile.takeScreenshot('Screenshots/TC01_Failed.png')
	throw e
} finally {
	Mobile.closeApplication()
}