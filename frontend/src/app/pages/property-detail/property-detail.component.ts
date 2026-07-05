import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { PropertyService } from '../../core/services/property.service';
import { EnquiryService } from '../../core/services/enquiry.service';
import { Property } from '../../core/models/property.model';

@Component({
  selector: 'app-property-detail',
  standalone: true,
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './property-detail.component.html',
  styleUrl: './property-detail.component.scss'
})
export class PropertyDetailComponent implements OnInit {
  property?: Property;
  notFound = false;
  activeImage = 0;
  submitting = false;
  submitted = false;
  submitError = '';

  form: ReturnType<FormBuilder['group']>;

  constructor(
    private route: ActivatedRoute,
    private propertyService: PropertyService,
    private enquiryService: EnquiryService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(/^[0-9+\-\s]{7,15}$/)]],
      message: ['']
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const slug = params.get('slug');
      if (!slug) return;
      this.notFound = false;
      this.submitted = false;
      this.activeImage = 0;
      this.propertyService.getBySlug(slug).subscribe({
        next: (property) => (this.property = property),
        error: () => (this.notFound = true)
      });
    });
  }

  setActiveImage(i: number): void {
    this.activeImage = i;
  }

  formatPrice(value: number): string {
    if (value >= 10000000) return `₹${(value / 10000000).toFixed(2)} Cr`;
    if (value >= 100000) return `₹${(value / 100000).toFixed(2)} Lac`;
    return `₹${value.toLocaleString('en-IN')}`;
  }

  hideImg(event: Event): void {
    (event.target as HTMLElement).style.display = 'none';
  }

  submit(): void {
    if (this.form.invalid || !this.property) {
      this.form.markAllAsTouched();
      return;
    }
    this.submitting = true;
    this.submitError = '';
    const value = this.form.value;
    this.enquiryService.submit({
      name: value.name!,
      email: value.email!,
      phone: value.phone!,
      message: value.message || '',
      propertySlug: this.property.slug,
      source: 'property_detail'
    }).subscribe({
      next: () => {
        this.submitting = false;
        this.submitted = true;
        this.form.reset();
      },
      error: () => {
        this.submitting = false;
        this.submitError = 'Something went wrong. Please try again in a moment.';
      }
    });
  }
}
