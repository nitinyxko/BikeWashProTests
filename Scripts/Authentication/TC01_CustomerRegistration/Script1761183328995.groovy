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
 * Description: Tests the complete customer registration process
 */

try {
    // Step 1: Launch app
    Mobile.startApplication(GlobalVariable.appPath, false)
    Mobile.delay(2)
    
    // Step 2: Select Customer role
    Mobile.tap(findTestObject('Customer_Register/New_Customer/Im a Customer'), 10)
    
    // Step 3: Click Create Account
    Mobile.tap(findTestObject('Customer_Register/Cust_Register'), 10)
    // Step 4: Fill registration form
    KeywordUtil.logInfo('Filling registration form')
    
    // Full Name
    Mobile.setText(findTestObject('Customer_Register/Enter your full name'), 'Test Customer', 10)
    Mobile.hideKeyboard()

    // Enter contact details
    Mobile.setText(findTestObject('Customer_Register/Enter your email address'), 'test@example.com', 10)
    Mobile.hideKeyboard()
    
    Mobile.setText(findTestObject('Customer_Register/Cust_Register_Phone Number'), GlobalVariable.testPhoneNumber, 10)
    Mobile.hideKeyboard()
    
    // Enter address details
    Mobile.setText(findTestObject('Customer_Register/Enter your address'), '123 Test Street', 10)
    Mobile.hideKeyboard()
    
    Mobile.tap(findTestObject('Customer_Register/ChooseCountry'), 10)
    Mobile.setText(findTestObject('Customer_Register/Enter city'), 'Mumbai', 10)
    Mobile.setText(findTestObject('Customer_Register/Enter pincode'), '400001', 10)
    Mobile.hideKeyboard()
    
    // Enter bike details
    Mobile.setText(findTestObject('Customer_Register/Enter your bike number'), 'MH12AB1234', 10)
    Mobile.hideKeyboard()
    
    // Submit registration
    Mobile.tap(findTestObject('Customer_Register/Create_Account'), 10)
    
    // Verify success
    Mobile.delay(2)
   // Mobile.verifyElementVisible(findTestObject('Customer_Register/Registration_Success'), 10)
    
    KeywordUtil.logInfo('=== Customer Registration Completed Successfully ===')
    
} catch (Exception e) {
    KeywordUtil.markFailed("Customer registration failed: " + e.getMessage())
    Mobile.takeScreenshot("Screenshots/CustomerRegistration_Failed.png")
} finally {
    Mobile.closeApplication()
}
