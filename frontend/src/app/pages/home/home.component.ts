import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { PropertyService } from '../../core/services/property.service';
import { StatsService } from '../../core/services/stats.service';
import { GalleryService } from '../../core/services/gallery.service';
import { TestimonialService } from '../../core/services/testimonial.service';
import { Property, PropertySummary } from '../../core/models/property.model';
import { CompanyStats } from '../../core/models/company-stats.model';
import { GalleryImage } from '../../core/models/gallery-image.model';
import { Testimonial } from '../../core/models/testimonial.model';
import { StatCounterComponent } from '../../shared/stat-counter/stat-counter.component';
import { TestimonialCardComponent } from '../../shared/testimonial-card/testimonial-card.component';
import { PropertyCardComponent } from '../../shared/property-card/property-card.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink, StatCounterComponent, TestimonialCardComponent, PropertyCardComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit, OnDestroy {
  flagship?: Property;
  properties: PropertySummary[] = [];
  stats?: CompanyStats;
  galleryPreview: GalleryImage[] = [];
  testimonials: Testimonial[] = [];
  activeTestimonial = 0;
  private carouselTimer?: ReturnType<typeof setInterval>;

  constructor(
    private propertyService: PropertyService,
    private statsService: StatsService,
    private galleryService: GalleryService,
    private testimonialService: TestimonialService
  ) {}

  ngOnInit(): void {
    this.propertyService.getAll().subscribe((list) => (this.properties = list));
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

  formatPrice(value: number): string {
    if (value >= 10000000) return `₹${(value / 10000000).toFixed(2)} Cr`;
    if (value >= 100000) return `₹${(value / 100000).toFixed(2)} Lac`;
    return `₹${value.toLocaleString('en-IN')}`;
  }

  hideImg(event: Event): void {
    (event.target as HTMLElement).style.display = 'none';
  }
}
