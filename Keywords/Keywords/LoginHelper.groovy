package Keywords

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable

public class LoginHelper {
    @Keyword
    def loginAsCustomer(String phone) {
        try {
            // Start the application
            Mobile.startApplication(GlobalVariable.appPath, false)
            Mobile.delay(2)

            // Select Customer role
            Mobile.tap(findTestObject('Customer_Register/New_Customer/Im a Customer'), 10)

            // Click Already have account
            Mobile.tap(findTestObject('Customer_Register/Already_have_account'), 10)

            // Enter phone number
            Mobile.setText(findTestObject('Customer_Register/New_Customer/Login_Phone Number'), phone, 10)
            Mobile.hideKeyboard()

            // Click login button
            Mobile.tap(findTestObject('Customer_Register/New_Customer/Login_btn'), 10)

            // Verify successful login
            Mobile.delay(2)
            Mobile.verifyElementVisible(findTestObject('Customer_Register/Customer_Home'), 10)

            return true
        } catch (Exception e) {
            KeywordUtil.markFailed("Failed to login as customer: " + e.getMessage())
            return false
        }
    }

    @Keyword
    def loginAsWasher(String phone) {
        try {
            // Start application
            Mobile.startApplication(GlobalVariable.appPath, false)
            Mobile.delay(2)

            // Select Washer role
            Mobile.tap(findTestObject('Washer/Im a Washer'), 10)

            // Click Login
            Mobile.tap(findTestObject('Washer/Washer_Login'), 10)

            // Enter phone number
            Mobile.setText(findTestObject('Washer/Login_Washer_Phone Number'), phone, 10)
            Mobile.hideKeyboard()

            // Click login button
            Mobile.tap(findTestObject('Washer/Login_btn'), 10)

            // Verify successful login
            Mobile.delay(2)
            Mobile.verifyElementVisible(findTestObject('Washer/Washer_Home'), 10)

            return true
        } catch (Exception e) {
            KeywordUtil.markFailed("Failed to login as washer: " + e.getMessage())
            return false
        }
    }

    @Keyword
    def logout() {
        try {
            // Check if menu exists and logout
            if (Mobile.verifyElementPresent(findTestObject('Common/Menu_Button'), 5, FailureHandling.OPTIONAL)) {
                Mobile.tap(findTestObject('Common/Menu_Button'), 10)
                Mobile.tap(findTestObject('Common/Logout_Button'), 10)
                return true
            }
        } catch (Exception e) {
            KeywordUtil.markWarning("Logout failed: " + e.getMessage())
        }
        return false
    }

    @Keyword
    def closeApp() {
        Mobile.closeApplication()
    }
}