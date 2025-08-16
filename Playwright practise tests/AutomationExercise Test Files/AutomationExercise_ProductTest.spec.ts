import { test, expect } from '@playwright/test';
// BUGS

// Bug: Enter button does not register when searching for a product
// Steps to replicate:
// 1. Navigate https://www.automationexercise.com/products
// 2. Search for a product e,g Blue Top
// 3. Press enter
// Intended Result: Should only filter out a product called Blue Top
// Actual Result: Pressing Enter yielded no results, had to be done manually by clicking the search button

// Bug: Privacy and Cookie Settings not opening
// Steps to replicate:
// 1. Navigate https://www.automationexercise.com/
// 2. Scroll down to the bottom
// 3. Click on Privacy and Cookie Settings link highlight
// Intended Result: Should open Privacy and Cookie Settings Page
// Actual Result: Page doesn't open or does not exist
test('test', async ({ page }) => {
  await page.goto('https://www.automationexercise.com/');
  await page.getByRole('button', { name: 'Consent' }).click();
  await page.getByRole('link', { name: ' Products' }).click();
  await page.getByRole('textbox', { name: 'Search Product' }).click();
  await page.getByRole('textbox', { name: 'Search Product' }).fill('blue top');
  await page.getByRole('textbox', { name: 'Search Product' }).press('Enter');
  await page.getByRole('button', { name: '' }).click();
  await page.getByText('Add to cart').nth(1).click();
  await page.getByRole('button', { name: 'Continue Shopping' }).click();
  await page.getByRole('link', { name: ' View Product' }).click();
  await page.locator('#quantity').click();
  await page.locator('#quantity').fill('12');
  await page.getByRole('button', { name: ' Add to cart' }).click();
  await page.getByRole('button', { name: 'Continue Shopping' }).click();
  await page.getByRole('link', { name: 'Polo', exact: true }).click();
  await page.getByRole('dialog', { name: 'Search results for Polo' }).locator('iframe').contentFrame().locator('iframe[name="aswift_0"]').contentFrame().getByRole('link').filter({ hasText: /^$/ }).nth(1).click();
  await page.getByRole('textbox', { name: 'Your Name' }).click();
  await page.getByRole('textbox', { name: 'Your Name' }).press('CapsLock');
  await page.getByRole('textbox', { name: 'Your Name' }).press('CapsLock');
  await page.getByRole('textbox', { name: 'Your Name' }).fill('John');
  await page.getByRole('textbox', { name: 'Email Address', exact: true }).click();
  await page.getByRole('textbox', { name: 'Email Address', exact: true }).fill('dhrgd@gmail.com');
  await page.getByRole('textbox', { name: 'Add Review Here!' }).click();
  await page.getByRole('textbox', { name: 'Add Review Here!' }).fill('lorum ipsum');
  await page.getByRole('button', { name: 'Submit' }).click();
  await page.getByRole('textbox', { name: 'Your email address' }).click();
  await page.getByRole('textbox', { name: 'Your email address' }).fill('udhghjkg@gmail,com');
  await page.getByRole('button', { name: '' }).click();
  await page.getByRole('button', { name: 'Privacy and cookie settings' }).click();
});