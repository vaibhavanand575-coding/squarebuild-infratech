import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompanyStats } from '../models/company-stats.model';

@Injectable({ providedIn: 'root' })
export class StatsService {
  private readonly baseUrl = 'http://localhost:8081/api/stats';

  constructor(private http: HttpClient) {}

  getStats(): Observable<CompanyStats> {
    return this.http.get<CompanyStats>(this.baseUrl);
  }
}
