<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description>Customer flow tests including login and booking</description>
   <name>TS_CustomerFlow</name>
   <tag>customer</tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>1</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <rerunImmediately>false</rerunImmediately>
   <testSuiteGuid>customer-suite-guid</testSuiteGuid>
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
   <testCaseLink>
      <guid>tc06-guid</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Customer/TC06_BookPremiumWash</testCaseId>
   </testCaseLink>
   <testCaseLink>
      <guid>tc08-guid</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Customer/TC08_TrackOrder</testCaseId>
   </testCaseLink>
</TestSuiteEntity>