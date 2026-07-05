export interface EnquiryRequest {
  name: string;
  email: string;
  phone: string;
  propertySlug?: string;
  message?: string;
  source?: string;
}

export interface EnquiryResponse {
  id: number;
  receivedAt: string;
  message: string;
}
