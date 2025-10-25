package api

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import internal.GlobalVariable

/**
 * TC_API_Auth: Authentication API Tests
 */
class TC_API_Auth {
    @Keyword
    def testLogin() {
        // Test login API with mock data
        ResponseObject response = WS.sendRequest(findTestObject('API/Auth/Login', [
            'phoneNumber': GlobalVariable.testPhoneNumber
        ]))
        
        WS.verifyResponseStatusCode(response, 200)
        def jsonResponse = new JsonSlurper().parseText(response.getResponseText())
        WS.verifyElementPropertyValue(response, 'success', true)
        WS.verifyElementPropertyValue(response, 'data.token', jsonResponse.data.token)
        
        return jsonResponse.data.token
    }
    
    @Keyword
    def testRegistration() {
        // Test registration API with mock data
        ResponseObject response = WS.sendRequest(findTestObject('API/Auth/Register', [
            'phoneNumber': GlobalVariable.testPhoneNumber,
            'fullName': 'Test Washer',
            'email': 'washer@example.com',
            'address': '456 Washer Street',
            'city': 'Mumbai',
            'pincode': '400001',
            'experience': '2'
        ]))
        
        WS.verifyResponseStatusCode(response, 201)
        WS.verifyElementPropertyValue(response, 'success', true)
    }
}