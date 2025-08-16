import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
await page.goto('https://www.automationexercise.com/');
await page.getByRole('button', { name: 'Consent' }).click();
await page.getByRole('link', { name: 'Website for automation' }).click();
await page.getByRole('link', { name: ' Home' }).click();
await page.getByRole('heading', { name: 'AutomationExercise' }).click();
await page.getByRole('heading', { name: 'Full-Fledged practice website' }).click();
await page.getByRole('paragraph').filter({ hasText: 'All QA engineers can use this' }).click();
await page.getByRole('heading', { name: 'Category' }).click();
await page.getByRole('listitem').filter({ hasText: /^$/ }).first().click();
await page.getByRole('listitem').filter({ hasText: /^$/ }).nth(1).click();
await page.getByRole('listitem').filter({ hasText: /^$/ }).nth(2).click();
await page.getByRole('heading', { name: 'Features Items' }).click();
await page.getByRole('heading', { name: ' Women' }).click();
await page.getByRole('heading', { name: ' Men' }).click();
await page.getByRole('heading', { name: ' Kids' }).click();
await page.getByRole('link', { name: '(6) Polo' }).click();
await page.getByRole('link', { name: '(5) H&M' }).click();
await page.getByRole('link', { name: '(5) Madame' }).click();
await page.getByRole('link', { name: '(3) Mast & Harbour' }).click();
await page.getByRole('link', { name: '(4) Babyhug' }).click();
await page.getByRole('link', { name: '(3) Allen Solly Junior' }).click();
await page.getByRole('link', { name: '(3) Kookie Kids' }).click();
await page.getByRole('link', { name: '(5) Biba' }).click();
});

