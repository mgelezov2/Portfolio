import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/slider');
  await page.locator('#generate').fill('25');
  await page.getByRole('button', { name: 'Get Countries' }).click();
});
