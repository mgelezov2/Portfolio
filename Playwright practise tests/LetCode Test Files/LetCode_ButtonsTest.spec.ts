import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/button');
  await page.getByRole('button', { name: 'Goto Home and come back here' }).click();
  await page.goto('https://letcode.in/button');
  await page.getByRole('button', { name: 'Find Location' }).click();
  await page.getByRole('button', { name: 'Find the color of the button' }).click();
  await page.getByRole('button', { name: 'How tall & fat I am?' }).click();
  await page.getByRole('button', { name: 'Button Hold!' }).click();
  await page.mouse.down();
  await page.waitForTimeout(2000);
  await page.mouse.up();
  await expect(page.locator('text=Button has been long pressed')).toBeVisible();
});