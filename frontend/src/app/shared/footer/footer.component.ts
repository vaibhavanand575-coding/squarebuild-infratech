import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SITE_CONFIG, telLink } from '../../core/config/site-config';
import { PropertyService } from '../../core/services/property.service';
import { PropertySummary } from '../../core/models/property.model';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss'
})
export class FooterComponent implements OnInit {
  year = new Date().getFullYear();
  config = SITE_CONFIG;
  callHref = telLink();
  properties: PropertySummary[] = [];

  constructor(private propertyService: PropertyService) {}

  ngOnInit(): void {
    this.propertyService.getAll().subscribe((list) => (this.properties = list));
  }
}
