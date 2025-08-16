import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/home');
  await page.getByRole('link', { name: 'letcode' }).click();
  await page.getByRole('link', { name: 'Work-Space' }).click();
  await page.locator('a.navbar-link', { hasText: 'Products' }).hover();
  await page.getByRole('link', { name: 'Ortoni Report' }).click();
  await page.locator('a.navbar-link', { hasText: 'Products' }).hover();
  await page.getByRole('link', { name: 'LetXPath' }).click();
  await page.locator('a.navbar-link', { hasText: 'Products' }).hover();
  await page.getByRole('link', { name: 'Playwright Runner' }).click();
  await page.locator('a.navbar-link', { hasText: 'Grooming' }).hover();
  await page.getByRole('link', { name: 'Test Practice' }).click();
  await page.locator('a.navbar-link', { hasText: 'Grooming' }).hover();
  await page.getByRole('link', { name: 'Interview Q & A' }).click();
});
//await page.locator('a.navbar-link', { hasText: 'Products' }).hover();
//await page.getByRole('link', { name: 'Ortoni Report' }).click();