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
import groovy.json.JsonSlurper
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable

try {
	KeywordUtil.logInfo('=== API05: Create Booking ===')
	
	def bookingData = [
		serviceType: 'BASIC_WASH',
		bikeNumber: 'KA01AB1234',
		address: '123 MG Road, Bangalore',
		city: 'Bangalore',
		pincode: '560001',
		scheduledDate: '2025-10-26',
		scheduledTime: '10:00',
		paymentMethod: 'COD'
	]
	
	def response = CustomKeywords.'Keywords.apihelper.mockCreateBooking'(
		GlobalVariable.testCustomerUserId,
		bookingData
	)
	
	def json = new JsonSlurper().parseText(response)
	
	assert json.success == true : 'Booking creation failed'
	assert json.data.id != null : 'Booking ID missing'
	assert json.data.status == 'PENDING' : 'Status incorrect'
	assert json.data.servicePrice == 150 : 'Price incorrect'
	assert json.data.totalAmount == 165 : 'Total amount incorrect'
	
	GlobalVariable.lastBookingID = json.data.id
	
	KeywordUtil.markPassed("✅ Booking created: ${json.data.id}")
} catch (Exception e) {
	KeywordUtil.markFailed("❌ Booking creation failed: ${e.message}")
	throw e
}
