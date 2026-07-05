import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { WhatsappButtonComponent } from './shared/whatsapp-button/whatsapp-button.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, FooterComponent, WhatsappButtonComponent],
  template: `
    <a class="skip-link" href="#main-content">Skip to main content</a>
    <app-navbar></app-navbar>
    <main id="main-content">
      <router-outlet></router-outlet>
    </main>
    <app-footer></app-footer>
    <app-whatsapp-button></app-whatsapp-button>
  `,
  styles: [`
    .skip-link {
      position: absolute;
      left: -9999px;
      top: 0;
      background: var(--color-primary);
      color: var(--color-surface);
      padding: 0.75rem 1.25rem;
      z-index: 1000;
      border-radius: 0 0 8px 0;
      text-decoration: none;
      font-weight: 600;
    }
    .skip-link:focus {
      left: 0;
    }
  `]
})
export class AppComponent {}
