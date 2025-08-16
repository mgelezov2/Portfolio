import { test, expect } from '@playwright/test';
import path from 'path';
//ERROR IN FILE UPLOAD
test('test', async ({ page }) => {
  await page.goto('https://letcode.in/file');
  const filePath = '/home/michael/coder/project/workplace/tests/LetCode Test Files/Test File Folder/testfile.txt';
  await page.waitForSelector('input#myFile', { state: 'attached' });
  await page.setInputFiles('input#myFile', filePath);
  const downloadPromise = page.waitForEvent('download');
  await page.getByRole('link', { name: 'Download Excel' }).click();
  const download = await downloadPromise;
  const download1Promise = page.waitForEvent('download');
  await page.getByRole('link', { name: 'Download Pdf' }).click();
  const download1 = await download1Promise;
  const download2Promise = page.waitForEvent('download');
  await page.getByRole('link', { name: 'Download Text' }).click();
  const download2 = await download2Promise;
});