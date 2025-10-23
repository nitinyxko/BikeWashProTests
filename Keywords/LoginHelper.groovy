package keywords
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.annotation.Keyword
import internal.GlobalVariable

public class LoginHelper {
    @Keyword
    def loginAsCustomer(String phone, String otp) {
        Mobile.startApplication(GlobalVariable.appPath, false)
        Mobile.delay(3)
        
        if(Mobile.verifyElementExist(findTestObject('Object Repository/WelcomeScreen/btnLogin'), 5)) {
            Mobile.tap(findTestObject('Object Repository/WelcomeScreen/btnLogin'), 10)
        }
        
        Mobile.waitForElementPresent(findTestObject('Object Repository/LoginScreen/txtPhoneNumber'), 15)
        Mobile.setText(findTestObject('Object Repository/LoginScreen/txtPhoneNumber'), phone, 10)
        Mobile.hideKeyboard()
        Mobile.tap(findTestObject('Object Repository/LoginScreen/btnLogin'), 10)
        Mobile.delay(3)
        
        Mobile.waitForElementPresent(findTestObject('Object Repository/OTPScreen/txtOTP'), 15)
        Mobile.setText(findTestObject('Object Repository/OTPScreen/txtOTP'), otp, 10)
        Mobile.tap(findTestObject('Object Repository/OTPScreen/btnVerify'), 10)
        Mobile.delay(3)
    }
    
    @Keyword
    def logout() {
        try {
            if(Mobile.verifyElementExist(findTestObject('Object Repository/Common/btnMenu'), 5)) {
                Mobile.tap(findTestObject('Object Repository/Common/btnMenu'), 10)
                Mobile.tap(findTestObject('Object Repository/Common/btnLogout'), 10)
            }
        } catch(Exception e) {}
    }
}