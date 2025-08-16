import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/edit');
  await page.getByRole('textbox', { name: 'Enter first & last name' }).click();
  await page.getByRole('textbox', { name: 'Enter first & last name' }).fill('lorem ipsum');
  await page.locator('#join').click();
  await page.locator('#join').fill('lorem ipsum');
  await page.locator('#getMe').dblclick();
  await page.locator('#getMe').fill('lorem ipsum');
  await page.locator('#clearMe').click();
  await page.locator('#dontwrite').click();
});