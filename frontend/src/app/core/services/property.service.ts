import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Property, PropertySummary } from '../models/property.model';

@Injectable({ providedIn: 'root' })
export class PropertyService {
  private readonly baseUrl = 'http://localhost:8081/api/properties';

  constructor(private http: HttpClient) {}

  getAll(): Observable<PropertySummary[]> {
    return this.http.get<PropertySummary[]>(this.baseUrl);
  }

  getBySlug(slug: string): Observable<Property> {
    return this.http.get<Property>(`${this.baseUrl}/${slug}`);
  }
}
