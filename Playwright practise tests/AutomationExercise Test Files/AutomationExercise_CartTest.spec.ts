import { test, expect } from '@playwright/test';
// BUGS
// Bug: Sometimes the Cart highlight link doesn't highlight when returning or refreshing the page. 
// This also results in an error on development side where a pop up should be present when clicking the hightlight link and should close when testing automatically
// Steps to replicate:
// 1. Navigate to https://www.automationexercise.com/view_cart
// 2. click on highlighted link called here
// 3. Add an item to cart
// 4. Return to cart page
// 5. Cart link should be uninteractable
// Intended result: Navigation from the Cart page to Product page should allow the user to navigate with ease allowing them to add items to their cart
// Actual result: Test stopped at line 22 it was unable to click the close button for a pop up that comes with clicking the Cart highlight link
test('test', async ({ page }) => {
  await page.goto('https://www.automationexercise.com/');
  await page.getByRole('button', { name: 'Consent' }).click();
  await page.getByRole('link', { name: ' Cart' }).click();
  await page.getByRole('link', { name: 'Cart', exact: true }).click();
  await page.getByRole('button', { name: 'Close' }).click();
  await page.getByRole('link', { name: 'here' }).click();
  await page.goto('https://www.automationexercise.com/view_cart');
  await page.getByRole('link', { name: 'here' }).click();
  await page.getByRole('button', { name: 'Close' }).click();
  await page.getByRole('textbox', { name: 'Your email address' }).click();
  await page.getByRole('textbox', { name: 'Your email address' }).fill('fhgjlfjkl@gmail.com');
  await page.getByRole('button', { name: '' }).click();
  await page.getByRole('link', { name: 'here' }).click();
  await page.locator('.productinfo > .btn').first().click();
  await page.getByRole('link', { name: 'View Cart' }).click();
  await page.getByText('Proceed To Checkout').click();
  await page.getByRole('button', { name: 'Continue On Cart' }).click();
  await page.getByRole('cell', { name: '' }).locator('a').click();
});