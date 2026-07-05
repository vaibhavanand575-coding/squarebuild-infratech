import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EnquiryRequest, EnquiryResponse } from '../models/enquiry.model';

@Injectable({ providedIn: 'root' })
export class EnquiryService {
  private readonly baseUrl = 'http://localhost:8081/api/enquiries';

  constructor(private http: HttpClient) {}

  submit(request: EnquiryRequest): Observable<EnquiryResponse> {
    return this.http.post<EnquiryResponse>(this.baseUrl, request);
  }
}
