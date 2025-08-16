import { test, expect } from '@playwright/test';
// BUGS
// Bug:Once the user submits a file they get a pop to cancel or proceed options and a link to bring them back to home page but the pop up has not appeared
// Steps to recreate
// Step 1: Navigate to https://www.automationexercise.com/contact_us
// Step 2: Fill out details otherwise you will be notified to fill them in
// Step 3: Upload a dummy file
// Step 4: Submit file
// Step 5: Run Test
// Expected Result: The user should get a pop up giving the user to cancel or proceed options and notifiying them that the file is submitted with a link back to home page
// Actual Result: The test stops entirely on line 37 because the pop up never showed.
test('test', async ({ page }) => {
  await page.goto('https://www.automationexercise.com/');
  await page.getByRole('button', { name: 'Consent' }).click();
  await page.getByRole('link', { name: ' Contact us' }).click();
  await page.getByRole('textbox', { name: 'Name' }).click();
  await page.getByRole('textbox', { name: 'Name' }).fill('lorem');
  await page.getByRole('textbox', { name: 'Email', exact: true }).click();
  await page.getByRole('textbox', { name: 'Email', exact: true }).fill('lorem@gmail.com');
  await page.getByRole('textbox', { name: 'Name' }).click();
  await page.getByRole('textbox', { name: 'Name' }).fill('loremipsum');
  await page.getByRole('textbox', { name: 'Email', exact: true }).click();
  await page.getByRole('textbox', { name: 'Email', exact: true }).fill('loremipsum@gmail.com');
  await page.getByRole('textbox', { name: 'Subject' }).click();
  await page.getByRole('textbox', { name: 'Subject' }).fill('lorem ipsum');
  await page.getByRole('textbox', { name: 'Your Message Here' }).click();
  await page.getByRole('textbox', { name: 'Your Message Here' }).fill('lorem ipsum');
  await page.getByRole('button', { name: 'Choose File' }).click();
  const path = require('path');
  const filePath = path.resolve('/home/michael/coder/project/workplace/tests/New File.txt'); // Update this path
  await page.setInputFiles('input[type="file"]', filePath);
  page.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page.getByRole('button', { name: 'Submit' }).click();
  //await page.getByRole('link', { name: ' Home' }).click();
  await page.getByRole('link', { name: ' Contact us' }).click();
  await page.getByRole('link', { name: 'feedback@automationexercise.' }).click();
  await page.getByRole('textbox', { name: 'Your email address' }).click();
  await page.getByRole('textbox', { name: 'Your email address' }).fill('lorem@gmail.com');
  await page.getByRole('button', { name: '' }).click();
});