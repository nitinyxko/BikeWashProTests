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
 * TC02: Washer Registration
 * Description: Verify that a new washer can successfully register in the app
 * Priority: Critical
 * Tags: Authentication, Smoke, Washer
 */

try {
	// Launch application
	KeywordUtil.logInfo('Starting BikeWash Pro application for Washer registration')
	Mobile.startApplication(GlobalVariable.appPath, false)
	Mobile.delay(3)
	
	// Navigate to registration
	Mobile.waitForElementPresent(findTestObject('Object Repository/WelcomeScreen/btnGetStarted'), 30)
	Mobile.tap(findTestObject('Object Repository/WelcomeScreen/btnGetStarted'), 10)
	Mobile.delay(2)
	
	// Select Washer role
	KeywordUtil.logInfo('Selecting Washer role')
	Mobile.waitForElementPresent(findTestObject('Object Repository/RegisterScreen/btnWasherRole'), 15)
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/btnWasherRole'), 10)
	Mobile.delay(1)
	
	// Enter washer details
	KeywordUtil.logInfo('Entering washer phone number')
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/txtPhoneNumber'), 5)
	Mobile.setText(findTestObject('Object Repository/RegisterScreen/txtPhoneNumber'), '+919876543211', 10)
	
	KeywordUtil.logInfo('Entering washer name')
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/txtFullName'), 5)
	Mobile.setText(findTestObject('Object Repository/RegisterScreen/txtFullName'), 'Test Washer', 10)
	
	KeywordUtil.logInfo('Entering washer email')
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/txtEmail'), 5)
	Mobile.setText(findTestObject('Object Repository/RegisterScreen/txtEmail'), 'testwasher@bikewash.com', 10)
	
	// Enter additional washer-specific details
	KeywordUtil.logInfo('Entering washer experience')
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/txtExperience'), 5)
	Mobile.setText(findTestObject('Object Repository/RegisterScreen/txtExperience'), '2 years', 10)
	
	KeywordUtil.logInfo('Entering vehicle number')
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/txtVehicleNumber'), 5)
	Mobile.setText(findTestObject('Object Repository/RegisterScreen/txtVehicleNumber'), 'TN01AB5678', 10)
	
	Mobile.hideKeyboard()
	Mobile.delay(1)
	
	// Submit registration
	Mobile.tap(findTestObject('Object Repository/RegisterScreen/btnRegister'), 10)
	Mobile.delay(3)
	
	// Verify and enter OTP
	Mobile.waitForElementPresent(findTestObject('Object Repository/OTPScreen/txtOTP'), 15)
	Mobile.tap(findTestObject('Object Repository/OTPScreen/txtOTP'), 5)
	Mobile.setText(findTestObject('Object Repository/OTPScreen/txtOTP'), GlobalVariable.testOTP, 10)
	Mobile.tap(findTestObject('Object Repository/OTPScreen/btnVerify'), 10)
	Mobile.delay(3)
	
	// Verify navigation to Washer Home screen
	KeywordUtil.logInfo('Verifying Washer Home screen')
	Mobile.waitForElementPresent(findTestObject('Object Repository/WasherHomeScreen/lblDashboard'), 20)
	Mobile.verifyElementVisible(findTestObject('Object Repository/WasherHomeScreen/lblDashboard'), 10)
	
	KeywordUtil.markPassed('Washer registration completed successfully')
	
} catch (Exception e) {
	KeywordUtil.markFailed('Washer registration failed: ' + e.getMessage())
	Mobile.takeScreenshot()
	throw e
} finally {
	Mobile.closeApplication()
}

