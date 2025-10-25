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
	KeywordUtil.logInfo('=== API03: Verify OTP ===')
	
	def response = CustomKeywords.'Keywords.apihelper.mockVerifyOTP'(
		GlobalVariable.testCustomerUserId,
		GlobalVariable.testOTP
	)
	
	def json = new JsonSlurper().parseText(response)
	
	assert json.success == true : 'OTP verification failed'
	assert json.data.sessionToken != null : 'Session token missing'
	assert json.data.refreshToken != null : 'Refresh token missing'
	
	GlobalVariable.apiSessionToken = json.data.sessionToken
	GlobalVariable.apiRefreshToken = json.data.refreshToken
	
	KeywordUtil.markPassed('✅ OTP verified, session created')
} catch (Exception e) {
	KeywordUtil.markFailed("❌ OTP verification failed: ${e.message}")
	throw e
}
