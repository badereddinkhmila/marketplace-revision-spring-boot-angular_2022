export interface IProductModel {
  'nameProduct': string;
  'description': string;
  'isAvailable': boolean;
  'price': number;
  'quantity': number;
  'category_id': number;
  'product_images': Blob[];
}
