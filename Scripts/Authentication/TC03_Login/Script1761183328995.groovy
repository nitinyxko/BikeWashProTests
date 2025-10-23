import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/**
 * Test Case: Customer Login Flow
 * Description: Verifies that a customer can successfully log in to the app
 */

// Initialize test case
Mobile.comment('Starting Customer Login Test')
CustomKeywords.'Keywords.LoginHelper.loginAsCustomer'(GlobalVariable.testPhoneNumber)

// 1. Click "I'm a Customer" on landing page
Mobile.tap(findTestObject('Customer_Register/New_Customer/Im a Customer'), 10)

// 2. Click "Already have account"
Mobile.tap(findTestObject('Customer_Register/Already_have_account'), 10)

// 3. Enter phone number
Mobile.setText(findTestObject('Customer_Register/New_Customer/Login_Phone Number'), 
    GlobalVariable.testPhoneNumber, 10)

// 4. Hide keyboard and click login
Mobile.hideKeyboard()
Mobile.tap(findTestObject('Customer_Register/New_Customer/Login_btn'), 10)

// 5. Verify successful login
Mobile.delay(2)
Mobile.verifyElementVisible(findTestObject('Customer_Register/Customer_Home'), 10, FailureHandling.STOP_ON_FAILURE)

// Close app and restart for washer test
Mobile.closeApplication()
Mobile.delay(2)
Mobile.startApplication(GlobalVariable.appPath, true)

// Test Case 2: Washer Login Flow
Mobile.comment('Starting Washer Login Test')

// 1. Click "I'm a Washer" on landing page
Mobile.tap(findTestObject('Washer/Im a Washer'), 10)

// 2. Click Login button
Mobile.tap(findTestObject('Washer/Washer_Login'), 10)

// 3. Enter phone number
Mobile.setText(findTestObject('Washer/Login_Washer_Phone Number'), 
    GlobalVariable.testPhoneNumber, 10)

// 4. Hide keyboard and click login
Mobile.hideKeyboard()
Mobile.tap(findTestObject('Washer/Login_btn'), 10)

// 5. Verify successful login
Mobile.delay(2)
Mobile.verifyElementVisible(findTestObject('Washer/Washer_Home'), 10, FailureHandling.STOP_ON_FAILURE)

// Clean up
Mobile.closeApplication()