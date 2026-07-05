import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { PropertyService } from '../../core/services/property.service';
import { StatsService } from '../../core/services/stats.service';
import { GalleryService } from '../../core/services/gallery.service';
import { TestimonialService } from '../../core/services/testimonial.service';
import { EnquiryService } from '../../core/services/enquiry.service';
import { Property, PropertySummary } from '../../core/models/property.model';
import { CompanyStats } from '../../core/models/company-stats.model';
import { GalleryImage } from '../../core/models/gallery-image.model';
import { Testimonial } from '../../core/models/testimonial.model';
import { StatCounterComponent } from '../../shared/stat-counter/stat-counter.component';
import { TestimonialCardComponent } from '../../shared/testimonial-card/testimonial-card.component';
import { PropertyCardComponent } from '../../shared/property-card/property-card.component';
import { SITE_CONFIG } from '../../core/config/site-config';

interface InvestPoint {
  stat: string;
  label: string;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule, RouterLink, ReactiveFormsModule,
    StatCounterComponent, TestimonialCardComponent, PropertyCardComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit, OnDestroy {
  flagship?: Property;
  featuredProperties: PropertySummary[] = [];
  stats?: CompanyStats;
  galleryPreview: GalleryImage[] = [];
  testimonials: Testimonial[] = [];
  activeTestimonial = 0;
  config = SITE_CONFIG;

  submitting = false;
  submitted = false;
  submitError = '';
  form: ReturnType<FormBuilder['group']>;

  investPoints: InvestPoint[] = [
    { stat: '15 Min', label: 'Approx. drive to the upcoming Jewar International Airport' },
    { stat: '6-Lane', label: 'Direct access via the Yamuna Expressway corridor' },
    { stat: 'Multiple', label: 'Industrial & logistics hubs planned nearby' },
    { stat: '100%', label: 'Clear titles with transparent documentation' }
  ];

  private carouselTimer?: ReturnType<typeof setInterval>;

  constructor(
    private propertyService: PropertyService,
    private statsService: StatsService,
    private galleryService: GalleryService,
    private testimonialService: TestimonialService,
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
    this.propertyService.getAll().subscribe((list) => {
      this.featuredProperties = list.filter((p) => p.featured);
    });
    this.propertyService.getBySlug('urban-paradise-city').subscribe((property) => (this.flagship = property));
    this.statsService.getStats().subscribe((stats) => (this.stats = stats));
    this.galleryService.getAll().subscribe((images) => (this.galleryPreview = images.slice(0, 6)));
    this.testimonialService.getAll().subscribe((items) => {
      this.testimonials = items;
      this.startCarousel();
    });
  }

  ngOnDestroy(): void {
    if (this.carouselTimer) clearInterval(this.carouselTimer);
  }

  private startCarousel(): void {
    if (this.testimonials.length < 2) return;
    this.carouselTimer = setInterval(() => this.nextTestimonial(), 6000);
  }

  nextTestimonial(): void {
    this.activeTestimonial = (this.activeTestimonial + 1) % this.testimonials.length;
  }

  prevTestimonial(): void {
    this.activeTestimonial =
      (this.activeTestimonial - 1 + this.testimonials.length) % this.testimonials.length;
  }

  goToTestimonial(i: number): void {
    this.activeTestimonial = i;
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
      source: 'home_page'
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

  formatPrice(value: number): string {
    if (value >= 10000000) return `₹${(value / 10000000).toFixed(2)} Cr`;
    if (value >= 100000) return `₹${(value / 100000).toFixed(2)} Lac`;
    return `₹${value.toLocaleString('en-IN')}`;
  }

  hideImg(event: Event): void {
    (event.target as HTMLElement).style.display = 'none';
  }
}
