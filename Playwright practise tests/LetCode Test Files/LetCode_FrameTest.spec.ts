import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/frame/');
  await page.locator('iframe[name="firstFr"]').contentFrame().getByRole('textbox', { name: 'Enter name' }).click();
  await page.locator('iframe[name="firstFr"]').contentFrame().getByRole('textbox', { name: 'Enter name' }).fill('lorem');
  await page.locator('iframe[name="firstFr"]').contentFrame().getByRole('textbox', { name: 'Enter email' }).click();
  await page.locator('iframe[name="firstFr"]').contentFrame().getByRole('textbox', { name: 'Enter email' }).fill('ipsum');
  await page.locator('iframe[name="firstFr"]').contentFrame().locator('app-frame-content iframe').contentFrame().getByRole('textbox', { name: 'Enter email' }).click();
  await page.locator('iframe[name="firstFr"]').contentFrame().locator('app-frame-content iframe').contentFrame().getByRole('textbox', { name: 'Enter email' }).fill('loremipsum@gmail.com');
});