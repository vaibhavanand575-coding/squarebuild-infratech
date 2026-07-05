import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PropertyService } from '../../core/services/property.service';
import { PropertySummary, PropertyType } from '../../core/models/property.model';
import { PropertyCardComponent } from '../../shared/property-card/property-card.component';

type FilterType = PropertyType | 'ALL';

@Component({
  selector: 'app-properties',
  standalone: true,
  imports: [CommonModule, PropertyCardComponent],
  templateUrl: './properties.component.html',
  styleUrl: './properties.component.scss'
})
export class PropertiesComponent implements OnInit {
  allProperties: PropertySummary[] = [];
  filtered: PropertySummary[] = [];
  activeType: FilterType = 'ALL';
  sort: 'default' | 'price-asc' | 'price-desc' = 'default';

  typeFilters: { label: string; value: FilterType }[] = [
    { label: 'All', value: 'ALL' },
    { label: 'Plots', value: 'PLOT' },
    { label: 'Villas', value: 'VILLA' },
    { label: 'Farmhouses', value: 'FARMHOUSE' },
    { label: 'Commercial', value: 'COMMERCIAL' }
  ];

  constructor(private propertyService: PropertyService) {}

  ngOnInit(): void {
    this.propertyService.getAll().subscribe((list) => {
      this.allProperties = list;
      this.applyFilters();
    });
  }

  setType(type: FilterType): void {
    this.activeType = type;
    this.applyFilters();
  }

  setSort(sort: 'default' | 'price-asc' | 'price-desc'): void {
    this.sort = sort;
    this.applyFilters();
  }

  private applyFilters(): void {
    let result = this.activeType === 'ALL'
      ? [...this.allProperties]
      : this.allProperties.filter((p) => p.type === this.activeType);

    if (this.sort === 'price-asc') result = result.sort((a, b) => a.startingPrice - b.startingPrice);
    if (this.sort === 'price-desc') result = result.sort((a, b) => b.startingPrice - a.startingPrice);

    this.filtered = result;
  }
}
