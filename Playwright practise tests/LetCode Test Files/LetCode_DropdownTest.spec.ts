import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/dropdowns');
  await page.locator('#fruits').selectOption('0');
  await page.locator('#superheros').selectOption('aq');
  await page.locator('#lang').selectOption('py');
  await page.locator('#country').selectOption('Colombia');
});