export interface EnquiryRequest {
  name: string;
  email: string;
  phone: string;
  propertySlug?: string;
  message?: string;
}

export interface EnquiryResponse {
  id: string;
  receivedAt: string;
  message: string;
}
