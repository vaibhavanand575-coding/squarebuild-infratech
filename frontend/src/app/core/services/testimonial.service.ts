import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Testimonial } from '../models/testimonial.model';

@Injectable({ providedIn: 'root' })
export class TestimonialService {
  private readonly baseUrl = 'http://localhost:8081/api/testimonials';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Testimonial[]> {
    return this.http.get<Testimonial[]>(this.baseUrl);
  }
}
