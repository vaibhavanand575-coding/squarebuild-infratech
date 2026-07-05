export type PropertyType = 'PLOT' | 'VILLA' | 'FARMHOUSE';
export type PropertyStatus = 'AVAILABLE' | 'SOLD';

export interface PropertySummary {
  id: number;
  slug: string;
  title: string;
  type: PropertyType;
  price: number;
  size: number;
  sizeUnit: string;
  location: string;
  status: PropertyStatus;
  statusLabel: string;
  featured: boolean;
  heroImage: string;
}

export interface Property extends PropertySummary {
  description: string;
  totalUnits: number | null;
  imageUrls: string[];
  amenities: string[];
  createdAt: string;
}

export interface PropertyFilters {
  type?: PropertyType;
  status?: PropertyStatus;
  minPrice?: number;
  maxPrice?: number;
  minSize?: number;
  maxSize?: number;
}
