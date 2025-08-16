import { test, expect } from '@playwright/test';
//BUGS
//Bug: Playwright has a modal that doesn't appear due to the link connected to it appearing and sometimes not appearing at all during testing
//Steps to Replicate:
//Step 1: Start Testing and Navigate to https://letcode.in/selectable
//Step 2: Ensure if the link Playwright Training is visible
//Step 2.5: If it's not visible the test fails
// Expected Result: The test should select all of the options listed below 
// Actual Result: The test crashes when the Playwright Training Link doesn't appear resulting in being unable to close the modal.
test('test', async ({ page }) => {
  await page.goto('https://letcode.in/selectable');
  await page.getByText('Playwright Playwright').click();
  await page.getByRole('button', { name: 'Close' }).click();
  await page.getByText('Kurimurai').click();
  await page.getByText('Selenium', { exact: true }).click();
  await page.getByText('Protractor').click();
  await page.getByText('Appium').click();
  await page.getByText('TestNg').click();
  await page.locator('#container div').filter({ hasText: 'Postman' }).nth(1).click();
  await page.getByText('Cypress').click();
  await page.getByText('Webdriver.io').click();
  await page.getByText('LetCode', { exact: true }).click();
});