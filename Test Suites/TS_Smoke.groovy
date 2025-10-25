import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.verification.WSResponseManager

Suite.setDescription('Smoke test suite for critical paths')
Suite.setName('TS_Smoke')

Map suiteProperties = [
    'tag': 'smoke',
    'isRerun': false,
    'mailRecipient': '',
    'numberOfRerun': 1,
    'pageLoadTimeout': 30,
    'pageLoadTimeoutDefault': true,
    'rerunFailedTestCasesOnly': true,
    'rerunImmediately': false,
    'testSuiteGuid': 'smoke-suite-guid'
]

Suite.setProperties(suiteProperties)

TestCaseLink.addTestCase([
    'guid': 'tc01-guid',
    'isReuseDriver': false,
    'isRun': true,
    'testCaseId': 'Test Cases/Authentication/TC01_CustomerRegistration'
])

TestCaseLink.addTestCase([
    'guid': 'tc03-guid',
    'isReuseDriver': false,
    'isRun': true,
    'testCaseId': 'Test Cases/Authentication/TC03_Login',
    'variableLinks': [[
        'testDataLinkId': '',
        'type': 'DEFAULT',
        'value': '${GlobalVariable.testPhoneNumber}',
        'variableId': 'phone'
    ]]
])

TestCaseLink.addTestCase([
    'guid': 'tc05-guid',
    'isReuseDriver': false,
    'isRun': true,
    'testCaseId': 'Test Cases/Customer/TC05_BookBasicWash'
])