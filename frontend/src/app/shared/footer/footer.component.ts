import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SITE_CONFIG, telLink } from '../../core/config/site-config';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss'
})
export class FooterComponent {
  year = new Date().getFullYear();
  config = SITE_CONFIG;
  callHref = telLink();
}
