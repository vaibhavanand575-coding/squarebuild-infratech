import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { PropertySummary } from '../../core/models/property.model';

@Component({
  selector: 'app-property-card',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './property-card.component.html',
  styleUrl: './property-card.component.scss'
})
export class PropertyCardComponent {
  @Input({ required: true }) property!: PropertySummary;

  formatPrice(value: number): string {
    if (value >= 10000000) return `₹${(value / 10000000).toFixed(2)} Cr`;
    if (value >= 100000) return `₹${(value / 100000).toFixed(2)} Lac`;
    return `₹${value.toLocaleString('en-IN')}`;
  }

  hideImg(event: Event): void {
    (event.target as HTMLElement).style.display = 'none';
  }
}
