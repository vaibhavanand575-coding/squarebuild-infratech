import { CommonModule } from '@angular/common';
import { Component, HostListener, OnInit } from '@angular/core';
import { GalleryService } from '../../core/services/gallery.service';
import { GalleryImage, GalleryCategory } from '../../core/models/gallery-image.model';

type FilterCategory = GalleryCategory | 'ALL';

@Component({
  selector: 'app-gallery',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './gallery.component.html',
  styleUrl: './gallery.component.scss'
})
export class GalleryComponent implements OnInit {
  images: GalleryImage[] = [];
  filtered: GalleryImage[] = [];
  activeCategory: FilterCategory = 'ALL';
  lightboxIndex = -1;

  categories: { label: string; value: FilterCategory }[] = [
    { label: 'All', value: 'ALL' },
    { label: 'Exterior', value: 'EXTERIOR' },
    { label: 'Interior', value: 'INTERIOR' },
    { label: 'Amenities', value: 'AMENITY' },
    { label: 'Site Progress', value: 'SITE' }
  ];

  constructor(private galleryService: GalleryService) {}

  ngOnInit(): void {
    this.galleryService.getAll().subscribe((images) => {
      this.images = images;
      this.applyFilter();
    });
  }

  setCategory(category: FilterCategory): void {
    this.activeCategory = category;
    this.applyFilter();
  }

  private applyFilter(): void {
    this.filtered = this.activeCategory === 'ALL'
      ? this.images
      : this.images.filter((img) => img.category === this.activeCategory);
  }

  open(index: number): void {
    this.lightboxIndex = index;
  }

  close(): void {
    this.lightboxIndex = -1;
  }

  next(event?: Event): void {
    event?.stopPropagation();
    this.lightboxIndex = (this.lightboxIndex + 1) % this.filtered.length;
  }

  prev(event?: Event): void {
    event?.stopPropagation();
    this.lightboxIndex = (this.lightboxIndex - 1 + this.filtered.length) % this.filtered.length;
  }

  @HostListener('window:keydown', ['$event'])
  onKeydown(event: KeyboardEvent): void {
    if (this.lightboxIndex < 0) return;
    if (event.key === 'Escape') this.close();
    if (event.key === 'ArrowRight') this.next();
    if (event.key === 'ArrowLeft') this.prev();
  }

  hideImg(event: Event): void {
    (event.target as HTMLElement).style.display = 'none';
  }
}
