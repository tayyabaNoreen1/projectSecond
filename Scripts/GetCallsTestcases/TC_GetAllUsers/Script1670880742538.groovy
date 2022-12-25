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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

response = WS.sendRequest(findTestObject('GetCalls/GetUsers'), FailureHandling.OPTIONAL)

conditionStatusCode = WS.verifyResponseStatusCode(response, 200, FailureHandling.OPTIONAL)

if (conditionStatusCode) {
    KeywordUtil.logInfo('Status code is correct.')

    conditionSchema = WS.validateJsonAgainstSchema(response, 'Resources/GetUsers.txt', FailureHandling.OPTIONAL)

    if (conditionSchema) {
        KeywordUtil.logInfo('Response schema is correct.')

        conditionResultCount = WS.verifyElementsCount(response, 'data', 6, FailureHandling.OPTIONAL)
		if(conditionResultCount) {
			KeywordUtil.logInfo('Results count is correct.')
			
			conditionResponseParam = WS.verifyElementPropertyValue(response, 'data[1].first_name', 'Lindsay', FailureHandling.OPTIONAL)
			if(conditionResponseParam) {
				KeywordUtil.markPassed('User first name is correct.')
			}
			else {
				KeywordUtil.markFailedAndStop('Expected user is missing from response.')
			}
		}
		else {
			KeywordUtil.markError('Results count is incorrect.')
		}
		
    } else {
        KeywordUtil.markFailedAndStop('Response schema is incorrect.')
    }
} else {
    KeywordUtil.markFailedAndStop('Status code is incorrect')
}

