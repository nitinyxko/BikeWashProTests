<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description>Smoke test suite for critical paths</description>
   <name>TS_Smoke</name>
   <tag>smoke</tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>1</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>true</rerunFailedTestCasesOnly>
   <rerunImmediately>false</rerunImmediately>
   <testSuiteGuid>smoke-suite-guid</testSuiteGuid>
   <testCaseLink>
      <guid>tc01-guid</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Authentication/TC01_CustomerRegistration</testCaseId>
   </testCaseLink>
   <testCaseLink>
      <guid>tc03-guid</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Authentication/TC03_Login</testCaseId>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value>${GlobalVariable.testPhoneNumber}</value>
         <variableId>phone</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>tc05-guid</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Customer/TC05_BookBasicWash</testCaseId>
   </testCaseLink>
</TestSuiteEntity>