import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/shadow');
  await page.locator('#fname').click();
  await page.locator('#fname').fill('loerm');
  await page.locator('my-web-component').click();
  await page.locator('#close-shadow').click();
  await page.locator('my-web-component').click();
  await page.locator('#close-shadow').click();
});