import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GalleryImage } from '../models/gallery-image.model';

@Injectable({ providedIn: 'root' })
export class GalleryService {
  private readonly baseUrl = 'http://localhost:8081/api/gallery';

  constructor(private http: HttpClient) {}

  getAll(): Observable<GalleryImage[]> {
    return this.http.get<GalleryImage[]>(this.baseUrl);
  }
}
