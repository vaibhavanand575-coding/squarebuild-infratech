# Squarebuild Infratech

A modern, premium, mobile-first company website for Squarebuild Infratech Pvt. Ltd. —
a real-estate developer building plotted townships, villas and farmhouses near the
upcoming Jewar International Airport. Built to build trust with investors and
end-users, and to generate leads (enquiry forms + WhatsApp/call).

## Structure

```
squarebuild-infratech/
  backend/    Spring Boot 3 / Java 17 REST API (port 8081)
  frontend/   Angular 17 standalone-component app (port 4200)
```

## How to run locally

### Prerequisites
- Java 17+
- Maven
- Node 18+
- Angular CLI (`npm install -g @angular/cli`, or use `npx ng` instead of `ng`)

### 1. Start the backend

```bash
cd backend
mvn spring-boot:run
```

Starts on `http://localhost:8081` using the **`dev`** Spring profile by default —
an H2 database file at `backend/data/infratech.mv.db` (zero setup, created
automatically, gitignored). Leave this terminal open.

On first boot, a `CommandLineRunner` seeds 3 sample properties (a plot township,
a farmhouse project, and a villa project). This only happens once — the seeder
checks the table is empty first, so restarts never duplicate data.

### 2. Start the frontend

In a separate terminal:

```bash
cd frontend
npm install
ng serve
```

Open `http://localhost:4200` in your browser. The frontend calls the backend at
`http://localhost:8081/api/...` (hardcoded in `frontend/src/app/core/services/*.ts`) —
both must be running for pages to show real data.

## API overview

**Public (no auth required):**
- `GET /api/properties?type=&status=&minPrice=&maxPrice=&minSize=&maxSize=` — filterable property list
- `GET /api/properties/{slug}` — full property detail
- `GET /api/testimonials`, `GET /api/gallery`, `GET /api/stats` — homepage content
- `POST /api/enquiries` — submit a contact / schedule-a-site-visit lead

**Admin (HTTP Basic auth required — see env vars below):**
- `POST /api/admin/properties` — create a property
- `PUT /api/admin/properties/{id}` — update a property
- `DELETE /api/admin/properties/{id}` — delete a property
- `GET /api/admin/enquiries` — view all submitted leads

## How to add or edit a property

There's no admin UI yet — use the admin API directly (e.g. via `curl`, Postman, or
Insomnia) with HTTP Basic auth (`ADMIN_USERNAME` / `ADMIN_PASSWORD`, defaults
`admin` / `admin123` locally):

```bash
curl -u admin:admin123 -X POST http://localhost:8081/api/admin/properties \
  -H "Content-Type: application/json" \
  -d '{
    "slug": "new-project-slug",
    "title": "New Project Name",
    "type": "VILLA",
    "description": "...",
    "price": 12000000,
    "size": 250,
    "sizeUnit": "Sq. Yards",
    "totalUnits": 40,
    "location": "Somewhere, State",
    "status": "AVAILABLE",
    "statusLabel": "New Launch",
    "featured": true,
    "heroImage": "https://.../hero.jpg",
    "imageUrls": ["https://.../1.jpg", "https://.../2.jpg"],
    "amenities": ["24/7 Security", "Clubhouse"]
  }'
```

`type` must be one of `PLOT` / `VILLA` / `FARMHOUSE`. `status` must be
`AVAILABLE` / `SOLD`. Setting `featured: true` surfaces the property in the
homepage "Property Highlights" section.

## Required environment variables

None of these are required for local development — sensible defaults keep it
working out of the box. Set them for production (see `backend/src/main/resources/application*.yml`):

| Variable | Purpose | Local default |
|---|---|---|
| `SPRING_PROFILES_ACTIVE` | `dev` (H2) or `prod` (Postgres) | `dev` |
| `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` | Postgres connection (prod profile only) | — |
| `ADMIN_USERNAME`, `ADMIN_PASSWORD` | Admin API credentials | `admin` / `admin123` |
| `CORS_ALLOWED_ORIGIN` | Frontend origin allowed to call the API | `http://localhost:4200` |
| `MAIL_ENABLED` | `true` to actually send enquiry notification emails | `false` |
| `SMTP_HOST`, `SMTP_PORT`, `SMTP_USERNAME`, `SMTP_PASSWORD` | SMTP credentials (only used if `MAIL_ENABLED=true`) | — |
| `MAIL_FROM`, `MAIL_NOTIFY_TO` | From/to addresses for enquiry notification emails | placeholder addresses |

With `MAIL_ENABLED=false` (or unset), enquiries still save normally — email
sending is skipped, not attempted. Even with it enabled, a failed send is
caught and logged, never breaks the lead-capture request (verified in testing:
submitting with `MAIL_ENABLED=true` and an unreachable SMTP host still returns
`201 Created`).

## Placeholders to replace before go-live

All in one place — `frontend/src/app/core/config/site-config.ts`:

```ts
export const SITE_CONFIG = {
  phone: '910000000000',        // ← WhatsApp/call number, intl format, no + or spaces
  phoneDisplay: '+91 00000 00000',
  email: 'info@squarebuildinfratech.com',
  address: 'Sector 62, Aligarh–Palwal Highway, Uttar Pradesh, India',
  workingHours: 'Mon – Sat, 9:30 AM – 6:30 PM',
  reraNumber: 'UPRERAPRJ-SAMPLE-000000',   // ← your real RERA registration number
  registrationLine: 'Squarebuild Infratech Pvt. Ltd. | CIN: U45200XX0000PTC000000'
};
```

This single file feeds the footer, the Contact page, and the floating
WhatsApp/call button — edit it once and every page updates.

**Images:** all property/gallery images are currently `picsum.photos` placeholders
(seeded in `backend/.../seed/DataSeeder.java` and `.../service/GalleryService.java`).
Replace `heroImage`/`imageUrls` via the admin API (existing properties) and swap
the gallery URLs directly in `GalleryService.java` for real project photography.

**SMTP:** set `MAIL_ENABLED=true` plus `SMTP_HOST`/`SMTP_PORT`/`SMTP_USERNAME`/
`SMTP_PASSWORD`/`MAIL_FROM`/`MAIL_NOTIFY_TO` once you have real email provider
credentials.

## What's inside

**Pages:** Home (hero, trust bar, why-invest stats, property highlights, amenities,
location map, gallery, testimonials, inline enquiry form), About Us, Properties
(type/status/price/size filters), Property Detail (gallery + schedule-a-visit form),
Gallery (category filters + lightbox), Contact (enquiry form).

**Design system:** green/gold/cream palette (60-30-10 rule — gold reserved for CTAs
and price/stat figures), Fraunces + Inter typography, 8px spacing scale — all as
CSS custom properties in `frontend/src/styles.scss`.

**Dynamic features:**
- All content served from a REST API — new properties/testimonials/gallery items
  show up without a frontend redeploy.
- Full property filtering (type, status, price range, size range) via the backend.
- Secured admin API for property CRUD and lead review.
- Working enquiry forms (Home, Contact, and per-property "Schedule a Site Visit")
  that persist to the database and tag their source (`home_page` / `contact_page` /
  `property_detail`).
- Opt-in email notification on new leads, safe by default.
- Sticky WhatsApp + call floating button.
- Animated count-up stats, image lightbox gallery, auto-rotating testimonials.
- Graceful image fallback (broken/slow images fade to a themed gradient).
- Accessible: WCAG AA color contrast throughout (verified — see below), skip-to-content
  link, visible focus states, labeled form fields, meaningful image alt text.

## Data storage

Properties and enquiries are persisted via Spring Data JPA — H2 (file-based) in
`dev`, PostgreSQL in `prod` (see env vars above). Testimonials, gallery images and
company stats stay in-memory (not in the agreed data model, no admin management
needed for this MVP).

## Accessibility notes

Color contrast was checked against WCAG AA (4.5:1 normal text / 3:1 large text)
for every text/background pairing in the design system. One real issue was found
and fixed: the raw accent gold (`#C9A227`, used on buttons where it pairs with
dark text) only reaches 2.42:1 as standalone text on light backgrounds — well
under AA. A separate `--color-accent-text` (`#8F6323`, 5.28:1) token is used for
all standalone gold text (prices, stat figures, star ratings) instead.

## Next steps (good extensions)

- Build an admin UI (the API already exists) instead of using `curl`/Postman
- Move to Flyway/Liquibase migrations before scaling past the MVP `ddl-auto: update`
- Add unit tests (JUnit backend, Jasmine/Karma frontend)
- Consider JWT auth for the admin API if a proper login screen gets built later

## Go-live checklist

Before pointing this at real traffic, supply/confirm:

- [ ] **RERA number** — replace `reraNumber` in `site-config.ts` with the real registration number
- [ ] **Phone number** — replace `phone`/`phoneDisplay` in `site-config.ts` (WhatsApp + call button + footer + Contact page all read from here)
- [ ] **Email address** — replace `email` in `site-config.ts`
- [ ] **Company registration line** — replace `registrationLine` in `site-config.ts` (CIN, etc.)
- [ ] **Real property data** — replace the 3 seeded properties via the admin API with actual current listings, pricing, and specs
- [ ] **Real photography** — replace all `picsum.photos` placeholder URLs (property heroes/galleries via admin API, site gallery in `GalleryService.java`)
- [ ] **SMTP credentials** — set `MAIL_ENABLED=true` + `SMTP_HOST`/`PORT`/`USERNAME`/`PASSWORD`/`MAIL_FROM`/`MAIL_NOTIFY_TO` so leads actually reach your inbox
- [ ] **Admin credentials** — set real `ADMIN_USERNAME`/`ADMIN_PASSWORD` (don't ship with `admin`/`admin123`)
- [ ] **Database** — provision Postgres, set `SPRING_PROFILES_ACTIVE=prod` + `DB_URL`/`DB_USERNAME`/`DB_PASSWORD`
- [ ] **Domain & hosting** — decide where the frontend (static build via `ng build`) and backend (JAR via `mvn package`) will run, and update `CORS_ALLOWED_ORIGIN` plus the frontend's hardcoded API base URLs to match the real backend domain
- [ ] **Possession date copy** — the homepage trust bar currently says "Updated Regularly on Site Visits" as a placeholder; add a real date if you have one
- [ ] **Legal review of marketing copy** — the "Why Invest Here" stat points were deliberately written to avoid promising specific returns/appreciation figures (only factual distances/infrastructure claims); have legal confirm this reads as compliant for your jurisdiction before launch
