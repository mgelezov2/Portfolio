import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/product/1');
  await page.getByRole('button', { name: '' }).click();
  await page.getByRole('textbox', { name: 'Enter Username' }).click();
  await page.getByRole('textbox', { name: 'Enter Username' }).fill('mor_2314');
  await page.getByRole('textbox', { name: 'Enter Password' }).click();
  await page.getByRole('textbox', { name: 'Enter Password' }).fill('83r5^_');
  await page.getByRole('button', { name: 'Login' }).click();
  await page.getByRole('button', { name: '' }).click();
});