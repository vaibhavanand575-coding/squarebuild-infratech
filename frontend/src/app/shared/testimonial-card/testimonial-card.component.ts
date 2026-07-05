import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Testimonial } from '../../core/models/testimonial.model';

@Component({
  selector: 'app-testimonial-card',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="testimonial-card">
      <div class="stars">
        <span *ngFor="let i of stars(testimonial.rating)">&#9733;</span>
        <span class="muted" *ngFor="let i of stars(5 - testimonial.rating)">&#9733;</span>
      </div>
      <p class="message">&ldquo;{{ testimonial.message }}&rdquo;</p>
      <div class="author">
        <strong>{{ testimonial.name }}</strong>
        <span>{{ testimonial.location }}</span>
      </div>
    </div>
  `,
  styles: [`
    .testimonial-card {
      background: var(--white);
      border-radius: var(--radius);
      box-shadow: var(--shadow-sm);
      padding: 2rem;
      height: 100%;
      display: flex;
      flex-direction: column;
    }
    .stars { color: var(--color-accent-text); margin-bottom: 1rem; letter-spacing: 2px; }
    .stars .muted { color: var(--border); }
    .message { flex: 1; color: var(--ink); font-style: italic; }
    .author { display: flex; flex-direction: column; margin-top: 1rem; }
    .author strong { color: var(--navy); }
    .author span { font-size: 0.85rem; color: var(--ink-light); }
  `]
})
export class TestimonialCardComponent {
  @Input({ required: true }) testimonial!: Testimonial;

  stars(n: number): number[] {
    return Array.from({ length: Math.max(n, 0) });
  }
}
