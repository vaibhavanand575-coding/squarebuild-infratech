# Squarebuild Infratech

A dynamic company website for Squarebuild Infratech Pvt. Ltd. — a real-estate developer
building plotted townships, villas, farmhouses and commercial spaces.

## Structure

```
squarebuild-infratech/
  backend/    Spring Boot 3 / Java 17 REST API (port 8081)
  frontend/   Angular 17 standalone-component app (port 4200)
```

## How to run

### Prerequisites
- Java 17+
- Maven
- Node 18+
- Angular CLI (`npm install -g @angular/cli`)

### 1. Start the backend

```bash
cd backend
mvn spring-boot:run
```

Starts on `http://localhost:8081`. Leave this terminal open.

Endpoints:
- `GET /api/properties` — property summaries (for listing/cards)
- `GET /api/properties/{slug}` — full property detail (features, amenities, gallery)
- `GET /api/testimonials` — buyer testimonials
- `GET /api/gallery` — gallery images, tagged by category
- `GET /api/stats` — company stats (square yards, projects, plots, farmhouses)
- `POST /api/enquiries` — submit a contact / schedule-a-visit enquiry

### 2. Start the frontend

In a separate terminal:

```bash
cd frontend
npm install
ng serve
```

Open `http://localhost:4200` in your browser.

## What's inside

**Pages:** Home, About Us, Properties (with type/price filters), Property Detail
(gallery + schedule-a-visit form), Gallery (category filters + lightbox), Contact
(enquiry form).

**Portfolio (seed data):** 4 projects spanning plots, villas, farmhouses and
commercial space — The Urban Paradise City, Green Meadows Farms, Royal Enclave
Villas, and Business Square.

**Dynamic features:**
- All content (properties, stats, testimonials, gallery) served from a REST API
  rather than hardcoded in markup — new projects can be added on the backend
  without a frontend redeploy.
- Property type + price filtering on the Properties page.
- Animated count-up stats that trigger on scroll into view.
- Image lightbox gallery with keyboard navigation (arrows/escape).
- Auto-rotating testimonials carousel with manual controls.
- Working enquiry forms (Contact page + per-property "Schedule a Site Visit")
  that POST to the backend and are stored server-side.
- Responsive mobile navigation.
- Graceful image fallback: broken/slow images fade out to a themed gradient
  instead of a broken-image icon.

## Data storage

Property, testimonial and gallery data is served from in-memory seed data in the
service layer (see `backend/src/main/java/com/squarebuild/infratech/service`).
Enquiries submitted via the contact/schedule-visit forms are stored in an
in-memory list for the lifetime of the process.

## Next steps (good extensions)

- Connect to a real database (Postgres + Spring Data JPA) for properties and enquiries
- Add an admin view to manage properties/gallery without redeploying
- Send enquiry submissions to email/CRM instead of in-memory storage
- Replace placeholder images with real project photography
- Add unit tests (JUnit backend, Jasmine/Karma frontend)
