export type PropertyType = 'PLOT' | 'VILLA' | 'FARMHOUSE' | 'COMMERCIAL';

export interface Feature {
  icon: string;
  title: string;
  description: string;
}

export interface PropertySummary {
  slug: string;
  name: string;
  type: PropertyType;
  location: string;
  status: string;
  startingPrice: number;
  priceUnit: string;
  heroImage: string;
  tagline: string;
}

export interface Property extends PropertySummary {
  description: string;
  areaSqYards: number;
  totalUnits: number;
  features: Feature[];
  amenities: string[];
  gallery: string[];
}
