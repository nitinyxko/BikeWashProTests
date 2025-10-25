import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable

/**
 * TC02: Washer Registration Flow
 * Description: Tests the complete washer registration process
 */

try {
    // Launch application
    KeywordUtil.logInfo('Starting BikeWash Pro application for Washer registration')
    Mobile.startApplication(GlobalVariable.appPath, false)
    Mobile.delay(2)

    // Select Washer role
    KeywordUtil.logInfo('Selecting Washer role')
    Mobile.tap(findTestObject('Washer/Im a Washer'), 10)

    // Navigate to registration
    KeywordUtil.logInfo('Navigating to washer registration')
    Mobile.tap(findTestObject('Washer/Washer_Register'), 10)
    
    // Enter phone number for registration
    KeywordUtil.logInfo('Entering phone number')
    Mobile.setText(findTestObject('Washer/Register_Washer_Phone Number'), GlobalVariable.testPhoneNumber, 10)
    Mobile.hideKeyboard()

    // Fill registration form
    KeywordUtil.logInfo('Filling washer registration form')
    Mobile.setText(findTestObject('Washer/Washer_Enter your full name'), 'Test Washer', 10)
    Mobile.hideKeyboard()

    Mobile.setText(findTestObject('Washer/Washer_Enter your email address'), 'washer@example.com', 10)
    Mobile.hideKeyboard()

    Mobile.setText(findTestObject('Washer/Washer_Enter your address'), '456 Washer Street', 10)
    Mobile.hideKeyboard()

    Mobile.tap(findTestObject('Washer/Washer_Choosecountry'), 10)
    Mobile.setText(findTestObject('Washer/Washer_Enter city'), 'Mumbai', 10)
    Mobile.hideKeyboard()

    Mobile.setText(findTestObject('Washer/Washer_Enter pincode'), '400001', 10)
    Mobile.hideKeyboard()

    Mobile.setText(findTestObject('Washer/Washer_Enter years of experience'), '2', 10)
    Mobile.hideKeyboard()

    // Create account
    KeywordUtil.logInfo('Creating washer account')
    Mobile.tap(findTestObject('Washer/Washer_Createaccount'), 10)

    // Wait for registration to complete
    Mobile.delay(3)
    KeywordUtil.markPassed('Washer Registration Completed Successfully')

} catch (Exception e) {
    KeywordUtil.markFailed("Washer registration failed: " + e.getMessage())
    Mobile.takeScreenshot("Screenshots/WasherRegistration_Failed.png")
} finally {
    Mobile.closeApplication()
}