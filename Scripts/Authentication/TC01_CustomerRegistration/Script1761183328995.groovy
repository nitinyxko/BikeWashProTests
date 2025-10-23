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

/**
 * TC01: Customer Registration
 * Description: Verify that a new customer can successfully register in the BikeWash Pro app
 * Priority: Critical
 * Tags: Authentication, Smoke, Customer
 */

try {
	// Step 1: Launch BikeWash Pro application
	KeywordUtil.logInfo('Starting BikeWash Pro application')
	Mobile.startApplication(GlobalVariable.appPath, false)
	Mobile.delay(3)
	
	// Step 2: Wait for Welcome screen to load
	KeywordUtil.logInfo('Waiting for Welcome screen')
	Mobile.waitForElementPresent(findTestObject('Object Repository/WelcomeScreen/btnGetStarted'), 30)
	
	// Step 3: Verify Welcome screen elements
	Mobile.verifyElementVisible(findTestObject('Object Repository/WelcomeScreen/lblAppTitle'), 10)
	Mobile.verifyElementText(findTestObject('Object Repository/WelcomeScreen/lblAppTitle'), 'BikeWash Pro', FailureHandling.STOP_ON_FAILURE)
	
	// Step 4: Tap Get Started button
	KeywordUtil.logInfo('Tapping Get Started button')
	Mobile.tap(findTestObject('Object Repository/WelcomeScreen/btnGetStarted'), 10)
	Mobile.delay(2)
	
	// Step 5: Wait for Registration screen
	Mobile.waitForElementPresent(findTestObject('Object Repository/RegisterScreen/btnCustomerRole'), 15)
	
	// Step 6: Select Customer role
	KeywordUtil.logInfo('Selecting Customer role')
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/btnCustomerRole'), 10)
	Mobile.delay(1)
	
	// Step 7: Verify Customer role is selected
	Mobile.verifyElementChecked(findTestObject('Object Repository/RegisterScreen/btnCustomerRole'), 5)
	
	// Step 8: Enter phone number
	KeywordUtil.logInfo('Entering phone number: ' + GlobalVariable.testPhoneNumber)
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/txtPhoneNumber'), 5)
	Mobile.setText(findTestObject('Object Repository/RegisterScreen/txtPhoneNumber'), GlobalVariable.testPhoneNumber, 10)
	
	// Step 9: Enter full name
	KeywordUtil.logInfo('Entering customer name')
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/txtFullName'), 5)
	Mobile.setText(findTestObject('Object Repository/RegisterScreen/txtFullName'), 'Test Customer', 10)
	
	// Step 10: Enter email address
	KeywordUtil.logInfo('Entering email address')
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/txtEmail'), 5)
	Mobile.setText(findTestObject('Object Repository/RegisterScreen/txtEmail'), 'testcustomer@bikewash.com', 10)
	
	// Step 11: Hide keyboard if visible
	Mobile.hideKeyboard()
	Mobile.delay(1)
	
	// Step 12: Tap Register button
	KeywordUtil.logInfo('Tapping Register button')
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/btnRegister'), 10)
	Mobile.delay(3)
	
	// Step 13: Wait for OTP screen
	KeywordUtil.logInfo('Waiting for OTP screen')
	Mobile.waitForElementPresent(findTestObject('Object Repository/OTPScreen/txtOTP'), 15)
	
	// Step 14: Verify OTP screen loaded
	Mobile.verifyElementVisible(findTestObject('Object Repository/OTPScreen/lblOTPTitle'), 10)
	
	// Step 15: Enter OTP code
	KeywordUtil.logInfo('Entering OTP: ' + GlobalVariable.testOTP)
	Mobile.tap(findTestObject('Object Repository/OTPScreen/txtOTP'), 5)
	Mobile.setText(findTestObject('Object Repository/OTPScreen/txtOTP'), GlobalVariable.testOTP, 10)
	
	// Step 16: Tap Verify button
	KeywordUtil.logInfo('Tapping Verify button')
	Mobile.tap(findTestObject('Object Repository/OTPScreen/btnVerify'), 10)
	Mobile.delay(3)
	
	// Step 17: Verify navigation to Customer Home screen
	KeywordUtil.logInfo('Verifying Customer Home screen')
	Mobile.waitForElementPresent(findTestObject('Object Repository/CustomerHomeScreen/lblWelcome'), 20)
	Mobile.verifyElementVisible(findTestObject('Object Repository/CustomerHomeScreen/lblWelcome'), 10)
	
	// Step 18: Verify welcome message contains customer name
	String welcomeText = Mobile.getText(findTestObject('Object Repository/CustomerHomeScreen/lblWelcome'), 5)
	assert welcomeText.contains('Test Customer') : 'Welcome message does not contain customer name'
	
	KeywordUtil.markPassed('Customer registration completed successfully')
	
} catch (Exception e) {
	KeywordUtil.markFailed('Customer registration failed: ' + e.getMessage())
	Mobile.takeScreenshot()
	throw e
} finally {
	// Step 19: Close application
	Mobile.closeApplication()
}
