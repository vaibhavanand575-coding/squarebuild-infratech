import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { EnquiryService } from '../../core/services/enquiry.service';
import { PropertyService } from '../../core/services/property.service';
import { PropertySummary } from '../../core/models/property.model';
import { SITE_CONFIG, telLink } from '../../core/config/site-config';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent implements OnInit {
  properties: PropertySummary[] = [];
  submitting = false;
  submitted = false;
  submitError = '';
  config = SITE_CONFIG;
  callHref = telLink();

  form: ReturnType<FormBuilder['group']>;

  constructor(
    private fb: FormBuilder,
    private enquiryService: EnquiryService,
    private propertyService: PropertyService,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(/^[0-9+\-\s]{7,15}$/)]],
      propertySlug: [''],
      message: ['']
    });
  }

  ngOnInit(): void {
    this.propertyService.getAll().subscribe((list) => (this.properties = list));
    const preselect = this.route.snapshot.queryParamMap.get('property');
    if (preselect) this.form.patchValue({ propertySlug: preselect });
  }

  submit(): void {
    if (this.form.invalid) {
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
      propertySlug: value.propertySlug || undefined,
      source: 'contact_page'
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
