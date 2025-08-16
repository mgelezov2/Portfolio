import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/radio');
  await page.locator('#yes').check();
  await page.locator('#two').check();
  await page.getByText('Yes').nth(1).click();
  await page.locator('#nobug').check();
  await page.locator('#bug').check();
  await page.locator('#nobug').check();
  await page.locator('#two').check();
  await page.getByText('Yes').nth(1).click();
  await page.getByText('Yes').nth(1).click();
  await page.getByRole('radio', { name: 'Foo' }).check();
  await page.getByRole('radio', { name: 'Going', exact: true }).check();
  await page.getByRole('radio', { name: 'Not going' }).check();
  await page.getByText('Remember me').click();
  await page.getByRole('checkbox', { name: 'Remember me' }).check();
  await page.getByText('No').first().click();
  await page.getByText('Yes').first().click();
  await page.getByText('Remember me').click();
  await page.getByText('Remember me').click();
  await page.getByText('I agree to the FAKE terms and').click();
});