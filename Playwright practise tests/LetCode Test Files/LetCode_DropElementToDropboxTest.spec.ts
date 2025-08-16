import { test, expect } from '@playwright/test';

test('drag and drop element on LetCode', async ({ page }) => {
  await page.goto('https://letcode.in/droppable');

  const source = page.locator('#draggable');
  const target = page.locator('#droppable');

  
  const sourceBox = await source.boundingBox();
  const targetBox = await target.boundingBox();

  if (sourceBox && targetBox) {
    const startX = sourceBox.x + sourceBox.width / 2;
    const startY = sourceBox.y + sourceBox.height / 2;
    const endX = targetBox.x + targetBox.width / 2;
    const endY = targetBox.y + targetBox.height / 2;

    
    await page.mouse.move(startX, startY);
    await page.mouse.down();
    await page.mouse.move(endX, endY, { steps: 15 });
    await page.mouse.up();

    await page.waitForTimeout(1000);
  }
});