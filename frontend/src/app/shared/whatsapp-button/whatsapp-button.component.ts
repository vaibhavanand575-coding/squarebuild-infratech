import { Component } from '@angular/core';
import { SITE_CONFIG, telLink, whatsAppLink } from '../../core/config/site-config';

@Component({
  selector: 'app-whatsapp-button',
  standalone: true,
  template: `
    <div class="floating-actions">
      <a
        class="fab fab-call"
        [href]="callHref"
        aria-label="Call Squarebuild Infratech"
        title="Call us"
      >
        <svg viewBox="0 0 24 24" width="24" height="24" aria-hidden="true">
          <path fill="currentColor" d="M6.6 10.8c1.4 2.8 3.8 5.2 6.6 6.6l2.2-2.2c.3-.3.7-.4 1-.2 1.1.4 2.3.6 3.6.6.6 0 1 .4 1 1V20c0 .6-.4 1-1 1-9.4 0-17-7.6-17-17 0-.6.4-1 1-1h3.5c.6 0 1 .4 1 1 0 1.2.2 2.4.6 3.6.1.4 0 .8-.2 1L6.6 10.8z"/>
        </svg>
      </a>
      <a
        class="fab fab-whatsapp"
        [href]="whatsappHref"
        target="_blank"
        rel="noopener"
        aria-label="Message Squarebuild Infratech on WhatsApp"
        title="Chat on WhatsApp"
      >
        <svg viewBox="0 0 24 24" width="26" height="26" aria-hidden="true">
          <path fill="currentColor" d="M12 2a10 10 0 0 0-8.5 15.2L2 22l4.9-1.5A10 10 0 1 0 12 2zm0 18.2c-1.6 0-3.1-.4-4.5-1.2l-.3-.2-3 .9.9-2.9-.2-.3A8.2 8.2 0 1 1 12 20.2zm4.5-6.1c-.2-.1-1.5-.7-1.7-.8-.2-.1-.4-.1-.6.1-.2.2-.7.8-.8.9-.2.2-.3.2-.5.1-.2-.1-1-.4-1.9-1.2-.7-.6-1.2-1.4-1.3-1.6-.1-.2 0-.4.1-.5l.4-.4c.1-.2.2-.3.2-.5.1-.2 0-.4 0-.5-.1-.1-.6-1.5-.8-2-.2-.5-.4-.4-.6-.4h-.5c-.2 0-.5.1-.7.3-.2.2-1 1-1 2.4s1 2.8 1.1 3c.1.2 2 3.1 4.9 4.3.7.3 1.2.5 1.6.6.7.2 1.3.2 1.8.1.6-.1 1.5-.6 1.7-1.2.2-.6.2-1.1.1-1.2-.1-.1-.2-.2-.4-.3z"/>
        </svg>
      </a>
    </div>
  `,
  styles: [`
    .floating-actions {
      position: fixed;
      right: var(--space-3);
      bottom: var(--space-3);
      display: flex;
      flex-direction: column;
      gap: 0.75rem;
      z-index: 200;
    }

    .fab {
      width: 52px;
      height: 52px;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      color: var(--color-surface);
      box-shadow: 0 10px 24px rgba(27, 67, 50, 0.28);
      transition: transform 0.15s ease;
    }

    .fab:hover,
    .fab:focus-visible {
      transform: translateY(-3px);
    }

    .fab-call {
      background: var(--color-primary);
    }

    .fab-whatsapp {
      background: #25D366;
    }

    @media (max-width: 640px) {
      .floating-actions {
        right: var(--space-2);
        bottom: var(--space-2);
      }
      .fab {
        width: 46px;
        height: 46px;
      }
    }
  `]
})
export class WhatsappButtonComponent {
  callHref = telLink();
  whatsappHref = whatsAppLink();
}
