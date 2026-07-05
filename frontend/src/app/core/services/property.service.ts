import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Property, PropertyFilters, PropertySummary } from '../models/property.model';

@Injectable({ providedIn: 'root' })
export class PropertyService {
  private readonly baseUrl = 'http://localhost:8081/api/properties';

  constructor(private http: HttpClient) {}

  getAll(filters: PropertyFilters = {}): Observable<PropertySummary[]> {
    let params = new HttpParams();
    if (filters.type) params = params.set('type', filters.type);
    if (filters.status) params = params.set('status', filters.status);
    if (filters.minPrice != null) params = params.set('minPrice', filters.minPrice);
    if (filters.maxPrice != null) params = params.set('maxPrice', filters.maxPrice);
    return this.http.get<PropertySummary[]>(this.baseUrl, { params });
  }

  getBySlug(slug: string): Observable<Property> {
    return this.http.get<Property>(`${this.baseUrl}/${slug}`);
  }
}
