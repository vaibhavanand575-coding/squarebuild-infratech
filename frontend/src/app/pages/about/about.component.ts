import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { StatsService } from '../../core/services/stats.service';
import { CompanyStats } from '../../core/models/company-stats.model';
import { StatCounterComponent } from '../../shared/stat-counter/stat-counter.component';

interface Value {
  icon: string;
  title: string;
  description: string;
}

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [CommonModule, RouterLink, StatCounterComponent],
  templateUrl: './about.component.html',
  styleUrl: './about.component.scss'
})
export class AboutComponent implements OnInit {
  stats?: CompanyStats;

  values: Value[] = [
    { icon: 'T', title: 'Transparency', description: 'Clear paperwork, verified titles, and honest timelines at every stage of the buying process.' },
    { icon: 'Q', title: 'Quality Construction', description: 'Modern engineering standards applied consistently across plots, villas and commercial builds.' },
    { icon: 'D', title: 'On-Time Delivery', description: 'Infrastructure and possession milestones tracked and communicated to every buyer.' },
    { icon: 'V', title: 'Long-Term Value', description: 'Locations chosen for genuine growth potential, not just short-term sales appeal.' }
  ];

  constructor(private statsService: StatsService) {}

  ngOnInit(): void {
    this.statsService.getStats().subscribe((stats) => (this.stats = stats));
  }
}
