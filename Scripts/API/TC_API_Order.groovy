package api

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import internal.GlobalVariable

/**
 * TC_API_Order: Order Management API Tests
 */
class TC_API_Order {
    @Keyword
    def testBookBasicWash() {
        // Get auth token first
        def authToken = new TC_API_Auth().testLogin()
        
        // Test book basic wash API with mock data
        ResponseObject response = WS.sendRequest(findTestObject('API/Order/BookBasicWash', [
            'Authorization': "Bearer ${authToken}",
            'bikeNumber': 'MH02AB1234',
            'location': [
                'address': '123 Customer Street',
                'city': 'Mumbai',
                'pincode': '400001'
            ]
        ]))
        
        WS.verifyResponseStatusCode(response, 201)
        WS.verifyElementPropertyValue(response, 'success', true)
        def jsonResponse = new JsonSlurper().parseText(response.getResponseText())
        WS.verifyElementPropertyValue(response, 'data.orderId', jsonResponse.data.orderId)
        
        return jsonResponse.data.orderId
    }
    
    @Keyword
    def testTrackOrder() {
        // Book an order first
        def orderId = testBookBasicWash()
        
        // Test track order API
        ResponseObject response = WS.sendRequest(findTestObject('API/Order/TrackOrder', [
            'orderId': orderId
        ]))
        
        WS.verifyResponseStatusCode(response, 200)
        WS.verifyElementPropertyValue(response, 'success', true)
        WS.verifyElementPropertyValue(response, 'data.status', 'PENDING')
    }
}