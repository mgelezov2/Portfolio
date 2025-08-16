import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://letcode.in/draggable');

  const box = await page.locator('#sample-box');
  const boundingBox = await box.boundingBox();

  if (boundingBox) {
    const startX = boundingBox.x + boundingBox.width / 2;
    const startY = boundingBox.y + boundingBox.height / 2;

    
    await page.mouse.move(startX, startY);
    await page.mouse.down();
    await page.mouse.move(startX - 300, startY + 200, { steps: 10 });
    await page.mouse.up();

    await page.waitForTimeout(1000); 
  }
});