import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PropertyService } from '../../core/services/property.service';
import { PropertyStatus, PropertySummary, PropertyType } from '../../core/models/property.model';
import { PropertyCardComponent } from '../../shared/property-card/property-card.component';

type TypeFilter = PropertyType | 'ALL';
type StatusFilter = PropertyStatus | 'ALL';

@Component({
  selector: 'app-properties',
  standalone: true,
  imports: [CommonModule, FormsModule, PropertyCardComponent],
  templateUrl: './properties.component.html',
  styleUrl: './properties.component.scss'
})
export class PropertiesComponent implements OnInit {
  results: PropertySummary[] = [];
  loading = false;

  activeType: TypeFilter = 'ALL';
  activeStatus: StatusFilter = 'ALL';
  minPrice: number | null = null;
  maxPrice: number | null = null;
  minSize: number | null = null;
  maxSize: number | null = null;
  sort: 'default' | 'price-asc' | 'price-desc' = 'default';

  typeFilters: { label: string; value: TypeFilter }[] = [
    { label: 'All Types', value: 'ALL' },
    { label: 'Plots', value: 'PLOT' },
    { label: 'Villas', value: 'VILLA' },
    { label: 'Farmhouses', value: 'FARMHOUSE' }
  ];

  statusFilters: { label: string; value: StatusFilter }[] = [
    { label: 'All Status', value: 'ALL' },
    { label: 'Available', value: 'AVAILABLE' },
    { label: 'Sold', value: 'SOLD' }
  ];

  constructor(private propertyService: PropertyService) {}

  ngOnInit(): void {
    this.applyFilters();
  }

  setType(type: TypeFilter): void {
    this.activeType = type;
    this.applyFilters();
  }

  setStatus(status: StatusFilter): void {
    this.activeStatus = status;
    this.applyFilters();
  }

  setSort(sort: 'default' | 'price-asc' | 'price-desc'): void {
    this.sort = sort;
    this.sortResults();
  }

  resetFilters(): void {
    this.activeType = 'ALL';
    this.activeStatus = 'ALL';
    this.minPrice = null;
    this.maxPrice = null;
    this.minSize = null;
    this.maxSize = null;
    this.sort = 'default';
    this.applyFilters();
  }

  applyFilters(): void {
    this.loading = true;
    this.propertyService
      .getAll({
        type: this.activeType === 'ALL' ? undefined : this.activeType,
        status: this.activeStatus === 'ALL' ? undefined : this.activeStatus,
        minPrice: this.minPrice ?? undefined,
        maxPrice: this.maxPrice ?? undefined,
        minSize: this.minSize ?? undefined,
        maxSize: this.maxSize ?? undefined
      })
      .subscribe((list) => {
        this.results = list;
        this.loading = false;
        this.sortResults();
      });
  }

  private sortResults(): void {
    if (this.sort === 'price-asc') this.results = [...this.results].sort((a, b) => a.price - b.price);
    if (this.sort === 'price-desc') this.results = [...this.results].sort((a, b) => b.price - a.price);
  }
}
