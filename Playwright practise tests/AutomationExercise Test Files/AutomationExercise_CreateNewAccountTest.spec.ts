import { test, expect } from '@playwright/test';
//BUGS
// Bug: Integrity error on sign up pops up after creating a new account
// Steps to Replicate:
// Step 1: Navigate to https://www.automationexercise.com/login and record test
// Step 2: Click New User Sign up
// Step 3: Fill in necessar details
// Step 4: Create Account
// Step 5: Run Test
// Expected Result: Once an account is created it should lead to a page notifying the user that their account is created and lead them back to home page
// Actual Result: An error pops up at the end of creating an account when the test run ends.
test('test', async ({ page }) => {
  await page.goto('https://www.automationexercise.com/login');
  await page.getByRole('button', { name: 'Consent' }).click();
  await page.getByRole('textbox', { name: 'Name' }).click();
  await page.getByRole('textbox', { name: 'Name' }).press('CapsLock');
  await page.getByRole('textbox', { name: 'Name' }).press('CapsLock');
  await page.getByRole('textbox', { name: 'Name' }).fill('Lorem ');
  await page.getByRole('textbox', { name: 'Name' }).press('CapsLock');
  await page.getByRole('textbox', { name: 'Name' }).press('CapsLock');
  await page.getByRole('textbox', { name: 'Name' }).fill('Lorem ');
  await page.getByRole('textbox', { name: 'Name' }).press('CapsLock');
  await page.getByRole('textbox', { name: 'Name' }).press('CapsLock');
  await page.getByRole('textbox', { name: 'Name' }).fill('Lorem Ipsum');
  await page.locator('form').filter({ hasText: 'Signup' }).getByPlaceholder('Email Address').click();
  await page.locator('form').filter({ hasText: 'Signup' }).getByPlaceholder('Email Address').fill('loremipsum@gmail.com');
  await page.getByRole('button', { name: 'Signup' }).click();
  await page.locator('#uniform-id_gender1').click();
  await page.getByRole('textbox', { name: 'Password *' }).click();
  await page.getByRole('textbox', { name: 'Password *' }).fill('loremipsum');
  await page.locator('#days').selectOption('10');
  await page.locator('#months').selectOption('3');
  await page.locator('#years').selectOption('2009');
  await page.getByRole('checkbox', { name: 'Sign up for our newsletter!' }).check();
  await page.getByRole('checkbox', { name: 'Receive special offers from' }).check();
  await page.getByRole('textbox', { name: 'First name *' }).click();
  await page.getByRole('textbox', { name: 'First name *' }).fill('lorem');
  await page.getByRole('textbox', { name: 'Last name *' }).click();
  await page.getByRole('textbox', { name: 'Last name *' }).fill('ipsum');
  await page.getByRole('textbox', { name: 'Company', exact: true }).click();
  await page.getByRole('textbox', { name: 'Company', exact: true }).fill('lorem');
  await page.getByRole('textbox', { name: 'Address * (Street address, P.' }).click();
  await page.getByRole('textbox', { name: 'Address * (Street address, P.' }).fill('lorem ipsum');
  await page.getByRole('textbox', { name: 'Address 2' }).click();
  await page.getByRole('textbox', { name: 'Address 2' }).fill('lorem ipsum');
  await page.getByLabel('Country *').selectOption('Australia');
  await page.getByRole('textbox', { name: 'State *' }).click();
  await page.getByRole('textbox', { name: 'State *' }).fill('lorem');
  await page.getByRole('textbox', { name: 'City * Zipcode *' }).click();
  await page.getByRole('textbox', { name: 'City * Zipcode *' }).fill('lorem');
  await page.locator('#zipcode').click();
  await page.locator('#zipcode').fill('23323');
  await page.getByRole('textbox', { name: 'Mobile Number *' }).click();
  await page.getByRole('textbox', { name: 'Mobile Number *' }).fill('89898896');
  await page.getByRole('button', { name: 'Create Account' }).click();
  await page.getByRole('link', { name: 'Continue' }).click();
});