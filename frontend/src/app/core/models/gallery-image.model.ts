export type GalleryCategory = 'EXTERIOR' | 'INTERIOR' | 'AMENITY' | 'SITE';

export interface GalleryImage {
  url: string;
  caption: string;
  category: GalleryCategory;
}
