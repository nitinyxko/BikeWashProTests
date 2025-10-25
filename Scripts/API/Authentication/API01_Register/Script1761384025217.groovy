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
	KeywordUtil.logInfo('=== API01: Register Customer ===')
	
	def response = CustomKeywords.'Keywords.apihelper.mockRegister'(
		GlobalVariable.testPhoneCustomer,
		'Test Customer',
		'customer@test.com',
		'CUSTOMER'
	)
	
	def json = new JsonSlurper().parseText(response)
	
	assert json.success == true : 'Registration failed'
	assert json.data.userId != null : 'User ID not generated'
	assert json.data.sessionToken != null : 'Session token not generated'
	
	GlobalVariable.apiSessionToken = json.data.sessionToken
	GlobalVariable.testCustomerUserId = json.data.userId
	
	KeywordUtil.markPassed("✅ Customer registered: ${json.data.userId}")
} catch (Exception e) {
	KeywordUtil.markFailed("❌ Registration failed: ${e.message}")
	throw e
}
