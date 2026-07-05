import { AfterViewInit, Component, ElementRef, Input, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-stat-counter',
  standalone: true,
  template: `
    <div class="stat-card">
      <div class="stat-value">{{ display.toLocaleString('en-IN') }}{{ suffix }}</div>
      <div class="stat-label">{{ label }}</div>
    </div>
  `,
  styles: [`
    .stat-card {
      background: var(--white);
      border-radius: var(--radius);
      box-shadow: var(--shadow-sm);
      padding: 1.75rem 1.25rem;
      text-align: center;
    }
    .stat-value {
      font-family: var(--font-serif);
      font-size: clamp(1.6rem, 3vw, 2.2rem);
      font-weight: 700;
      color: var(--color-accent);
    }
    .stat-label {
      margin-top: 0.35rem;
      color: var(--ink-light);
      font-size: 0.9rem;
    }
  `]
})
export class StatCounterComponent implements AfterViewInit, OnDestroy {
  @Input({ required: true }) value = 0;
  @Input() label = '';
  @Input() suffix = '';
  @Input() duration = 1400;

  display = 0;
  private observer?: IntersectionObserver;
  private frame?: number;

  constructor(private host: ElementRef<HTMLElement>) {}

  ngAfterViewInit(): void {
    this.observer = new IntersectionObserver(
      (entries) => {
        if (entries[0]?.isIntersecting) {
          this.animate();
          this.observer?.disconnect();
        }
      },
      { threshold: 0.3 }
    );
    this.observer.observe(this.host.nativeElement);
  }

  ngOnDestroy(): void {
    this.observer?.disconnect();
    if (this.frame) cancelAnimationFrame(this.frame);
  }

  private animate(): void {
    const start = performance.now();
    const target = this.value;

    const step = (now: number) => {
      const progress = Math.min((now - start) / this.duration, 1);
      const eased = 1 - Math.pow(1 - progress, 3);
      this.display = Math.round(target * eased);
      if (progress < 1) {
        this.frame = requestAnimationFrame(step);
      } else {
        this.display = target;
      }
    };

    this.frame = requestAnimationFrame(step);
  }
}
