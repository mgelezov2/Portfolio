import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/home');
  await page.getByRole('link', { name: 'letcode' }).click();
  await page.getByRole('link', { name: 'Work-Space' }).click();
  await page.getByText('Products').click();
  await page.getByLabel('main navigation').getByText('Grooming').click();
  await page.getByRole('link', { name: 'Courses' }).click();
  await page.getByRole('link', { name: 'Work-Space' }).click();
  await page.getByRole('link', { name: 'Page Object Model' }).click();
  await page.getByLabel('main navigation').getByText('Products').click();
  await page.getByRole('link', { name: 'Ortoni Report' }).click();
  await page.getByText('Products').click();
  await page.getByRole('link', { name: 'LetXPath' }).click();
  await page.getByText('Products').click();
  await page.getByRole('link', { name: 'Playwright Runner' }).click();
  await page.getByText('Grooming').click();
  await page.getByRole('link', { name: 'Test Practice' }).click();
  await page.getByRole('link', { name: 'Interview Q & A' }).click();
  await page.getByRole('link', { name: 'Courses' }).click();
  await page.locator('.card-footer-item').first().click();
});