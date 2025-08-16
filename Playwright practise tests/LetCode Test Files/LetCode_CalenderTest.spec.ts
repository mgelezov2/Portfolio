import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/calendar/');
  await page.getByRole('textbox', { name: 'Select your Birthday:' }).fill('2222-06-21');
  const calendarInput = page.locator('input[type="date"]');
  await expect(calendarInput).toBeVisible();
  await calendarInput.fill('2022-02-15');
  await expect(calendarInput).toHaveValue('2022-02-15');
});