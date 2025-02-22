import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  byteArrayToImageUrl(byteArray: number[]): string {
    if (!byteArray) return '';
    const uint8Array = new Uint8Array(byteArray);
    const blob = new Blob([uint8Array], { type: 'image/png' }); // Define o tipo da imagem
    return URL.createObjectURL(blob);
  }
}
