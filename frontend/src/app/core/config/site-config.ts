/**
 * Single place to update go-live placeholders (phone, RERA number, etc.)
 * before launch — see README "Placeholders to replace" section.
 */
export const SITE_CONFIG = {
  // WhatsApp/call number, international format, no '+' or spaces (e.g. 919876543210)
  phone: '919818501169',
  phoneDisplay: '+91 98185 01169',
  email: 'info@squarebuildinfratech.com',
  address: 'Sector 62, Aligarh–Palwal Highway, Uttar Pradesh, India',
  workingHours: 'Mon – Sat, 9:30 AM – 6:30 PM',
  reraNumber: 'UPRERAPRJ-SAMPLE-000000',
  registrationLine: 'Squarebuild Infratech Pvt. Ltd. | CIN: U45200XX0000PTC000000'
};

export function whatsAppLink(message = "Hi, I'd like to know more about your properties."): string {
  return `https://wa.me/${SITE_CONFIG.phone}?text=${encodeURIComponent(message)}`;
}

export function telLink(): string {
  return `tel:+${SITE_CONFIG.phone}`;
}
