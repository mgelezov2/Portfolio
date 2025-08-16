import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/home');
  await page.getByRole('button', { name: '₹ 109.95' }).click();
  await page.goto('https://letcode.in/product/1');
  await page.getByRole('button', { name: ' Add to Cart' }).click();
  await page.getByRole('button', { name: '' }).click();
  page.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page.getByRole('button', { name: 'Checkout' }).click();
  await page.getByRole('button', { name: 'Continue Shopping' }).click();
  await page.getByRole('button', { name: '₹ 109.95' }).click();
  await page.getByRole('button', { name: ' Add to Cart' }).click();
  await page.getByRole('button', { name: '' }).click();
  await page.getByRole('button', { name: '' }).click();
  await page.getByRole('button', { name: 'Continue Shopping' }).click();
  await page.getByRole('img', { name: 'Fjallraven - Foldsack No. 1' }).click();
});