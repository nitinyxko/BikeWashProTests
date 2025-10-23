import com.kms.katalon.core.annotation.*
import com.kms.katalon.core.context.*
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.KeywordUtil

class TestListener {
    @BeforeTestSuite
    def beforeTestSuite(TestSuiteContext ctx) {
        KeywordUtil.logInfo("=== TEST SUITE STARTED: ${ctx.getTestSuiteId()} ===")
    }
    
    @AfterTestSuite
    def afterTestSuite(TestSuiteContext ctx) {
        KeywordUtil.logInfo("=== TEST SUITE COMPLETED ===")
        try {
            Mobile.closeApplication()
        } catch(Exception e) {}
    }
    
    @BeforeTestCase
    def beforeTestCase(TestCaseContext ctx) {
        KeywordUtil.logInfo("--- TEST STARTED: ${ctx.getTestCaseId()} ---")
    }
    
    @AfterTestCase
    def afterTestCase(TestCaseContext ctx) {
        KeywordUtil.logInfo("--- TEST ENDED: ${ctx.getTestCaseStatus()} ---")
        if(ctx.getTestCaseStatus() == "FAILED") {
            try {
                Mobile.takeScreenshot("Screenshots/FAILED_${ctx.getTestCaseId()}.png")
            } catch(Exception e) {}
        }
        try {
            Mobile.closeApplication()
        } catch(Exception e) {}
    }
}