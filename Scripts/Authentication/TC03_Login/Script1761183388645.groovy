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
 * TC03: User Login
 * Description: Verify existing user can login successfully
 * Priority: Critical
 * Tags: Authentication, Smoke
 */

try {
	// Launch application
	KeywordUtil.logInfo('Starting BikeWash Pro application for Login')
	Mobile.startApplication(GlobalVariable.appPath, false)
	Mobile.delay(3)
	
	// Navigate to login screen
	Mobile.waitForElementPresent(findTestObject('Object Repository/WelcomeScreen/btnLogin'), 30)
	Mobile.tap(findTestObject('Object Repository/WelcomeScreen/btnLogin'), 10)
	Mobile.delay(2)
	
	// Enter credentials
	KeywordUtil.logInfo('Entering login phone number')
	Mobile.waitForElementPresent(findTestObject('Object Repository/LoginScreen/txtPhoneNumber'), 15)
	Mobile.tap(findTestObject('Object Repository/LoginScreen/txtPhoneNumber'), 5)
	Mobile.setText(findTestObject('Object Repository/LoginScreen/txtPhoneNumber'), GlobalVariable.testPhoneNumber, 10)
	
	Mobile.hideKeyboard()
	Mobile.delay(1)
	
	// Tap Login button
	Mobile.tap(findTestObject('Object Repository/LoginScreen/btnLogin'), 10)
	Mobile.delay(3)
	
	// Enter OTP
	Mobile.waitForElementPresent(findTestObject('Object Repository/OTPScreen/txtOTP'), 15)
	Mobile.tap(findTestObject('Object Repository/OTPScreen/txtOTP'), 5)
	Mobile.setText(findTestObject('Object Repository/OTPScreen/txtOTP'), GlobalVariable.testOTP, 10)
	Mobile.tap(findTestObject('Object Repository/OTPScreen/btnVerify'), 10)
	Mobile.delay(3)
	
	// Verify successful login
	Mobile.waitForElementPresent(findTestObject('Object Repository/CustomerHomeScreen/lblWelcome'), 20)
	Mobile.verifyElementVisible(findTestObject('Object Repository/CustomerHomeScreen/lblWelcome'), 10)
	
	KeywordUtil.markPassed('Login completed successfully')
	
} catch (Exception e) {
	KeywordUtil.markFailed('Login failed: ' + e.getMessage())
	Mobile.takeScreenshot()
	throw e
} finally {
	Mobile.closeApplication()
}

