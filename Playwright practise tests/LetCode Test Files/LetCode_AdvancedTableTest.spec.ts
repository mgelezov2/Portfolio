import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/advancedtable');
  await page.getByLabel('entries per page').selectOption('10');
  await page.getByRole('link', { name: '2' }).click();
  await page.getByRole('link', { name: 'Next' }).click();
  await page.getByRole('searchbox', { name: 'Search:' }).click();
  await page.getByRole('searchbox', { name: 'Search:' }).fill('oxford');
  await page.getByRole('searchbox', { name: 'Search:' }).click();
  await page.getByRole('searchbox', { name: 'Search:' }).fill('');
  await page.getByRole('link', { name: 'Last' }).click();
  await page.getByRole('link', { name: 'First' }).click();
});