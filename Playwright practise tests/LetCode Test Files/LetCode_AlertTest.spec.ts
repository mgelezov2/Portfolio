import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/alert');
  page.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page.getByRole('button', { name: 'Simple Alert' }).click();
  page.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page.getByRole('button', { name: 'Confirm Alert' }).click();
  page.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page.getByRole('button', { name: 'Prompt Alert' }).click();
  await page.getByRole('button', { name: 'Modern Alert' }).click();
  await page.getByRole('button', { name: 'close' }).click();
});