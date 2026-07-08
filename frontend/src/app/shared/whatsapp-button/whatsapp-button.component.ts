import { Component, HostListener } from '@angular/core';
import { telLink, whatsAppLink } from '../../core/config/site-config';

@Component({
  selector: 'app-whatsapp-button',
  standalone: true,
  imports: [],
  template: `
    <div class="floating-actions">
      <button
        class="fab fab-top"
        [class.visible]="showBackToTop"
        (click)="scrollToTop()"
        aria-label="Back to top"
        title="Back to top"
      >
        <svg viewBox="0 0 24 24" width="18" height="18" aria-hidden="true">
          <path fill="currentColor" d="M12 5l-7 7h4v7h6v-7h4z"/>
        </svg>
      </button>

      <a
        class="fab fab-whatsapp"
        [href]="whatsappHref"
        target="_blank"
        rel="noopener"
        aria-label="Message Squarebuild Infratech on WhatsApp"
        title="Chat on WhatsApp"
      >
        <svg viewBox="0 0 24 24" width="24" height="24" aria-hidden="true">
          <path fill="currentColor" d="M12 2a10 10 0 0 0-8.5 15.2L2 22l4.9-1.5A10 10 0 1 0 12 2zm0 18.2c-1.6 0-3.1-.4-4.5-1.2l-.3-.2-3 .9.9-2.9-.2-.3A8.2 8.2 0 1 1 12 20.2zm4.5-6.1c-.2-.1-1.5-.7-1.7-.8-.2-.1-.4-.1-.6.1-.2.2-.7.8-.8.9-.2.2-.3.2-.5.1-.2-.1-1-.4-1.9-1.2-.7-.6-1.2-1.4-1.3-1.6-.1-.2 0-.4.1-.5l.4-.4c.1-.2.2-.3.2-.5.1-.2 0-.4 0-.5-.1-.1-.6-1.5-.8-2-.2-.5-.4-.4-.6-.4h-.5c-.2 0-.5.1-.7.3-.2.2-1 1-1 2.4s1 2.8 1.1 3c.1.2 2 3.1 4.9 4.3.7.3 1.2.5 1.6.6.7.2 1.3.2 1.8.1.6-.1 1.5-.6 1.7-1.2.2-.6.2-1.1.1-1.2-.1-.1-.2-.2-.4-.3z"/>
        </svg>
      </a>

      <a class="fab-pill" [href]="callHref" aria-label="Call to book a site visit" title="Call us">
        <svg viewBox="0 0 24 24" width="18" height="18" aria-hidden="true">
          <path fill="currentColor" d="M6.6 10.8c1.4 2.8 3.8 5.2 6.6 6.6l2.2-2.2c.3-.3.7-.4 1-.2 1.1.4 2.3.6 3.6.6.6 0 1 .4 1 1V20c0 .6-.4 1-1 1-9.4 0-17-7.6-17-17 0-.6.4-1 1-1h3.5c.6 0 1 .4 1 1 0 1.2.2 2.4.6 3.6.1.4 0 .8-.2 1L6.6 10.8z"/>
        </svg>
        <span>Book a Visit</span>
      </a>
    </div>
  `,
  styles: [`
    .floating-actions {
      position: fixed;
      right: var(--space-3);
      bottom: var(--space-3);
      display: flex;
      align-items: center;
      gap: 0.75rem;
      z-index: 200;
    }

    .fab {
      width: 48px;
      height: 48px;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      color: var(--color-surface);
      border: none;
      cursor: pointer;
      box-shadow: 0 10px 24px rgba(15, 20, 15, 0.22);
      transition: transform 0.15s ease, opacity 0.2s ease, visibility 0.2s ease;
    }

    .fab:hover,
    .fab:focus-visible {
      transform: translateY(-3px);
    }

    .fab-whatsapp {
      background: #25D366;
    }

    .fab-top {
      background: var(--color-primary);
      opacity: 0;
      visibility: hidden;
      transform: translateY(6px);
    }

    .fab-top.visible {
      opacity: 1;
      visibility: visible;
      transform: translateY(0);
    }

    .fab-pill {
      display: inline-flex;
      align-items: center;
      gap: 0.6em;
      height: 48px;
      padding: 0 1.3rem;
      border-radius: 999px;
      background: var(--color-primary);
      color: var(--color-accent-on-dark);
      text-decoration: none;
      font-size: 0.78rem;
      font-weight: 500;
      text-transform: uppercase;
      letter-spacing: 0.07em;
      box-shadow: 0 10px 24px rgba(15, 20, 15, 0.22);
      transition: transform 0.15s ease, background-color 0.15s ease;
      white-space: nowrap;
    }

    .fab-pill:hover,
    .fab-pill:focus-visible {
      transform: translateY(-3px);
      background: var(--color-primary-dark);
    }

    @media (max-width: 640px) {
      .floating-actions {
        right: var(--space-2);
        bottom: var(--space-2);
        gap: 0.6rem;
      }
      .fab {
        width: 44px;
        height: 44px;
      }
      .fab-pill span { display: none; }
      .fab-pill {
        width: 44px;
        height: 44px;
        padding: 0;
        border-radius: 50%;
        justify-content: center;
      }
    }
  `]
})
export class WhatsappButtonComponent {
  callHref = telLink();
  whatsappHref = whatsAppLink();
  showBackToTop = false;

  @HostListener('window:scroll')
  onScroll(): void {
    this.showBackToTop = window.scrollY > 500;
  }

  scrollToTop(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
}
