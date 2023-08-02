import {
  Component,
  Inject,
  OnInit,
  OnChanges,
  SimpleChanges,
  OnDestroy,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppState } from '@src/store/app.states';
import { LoginUser } from '@src/store/actions/auth.actions';
import { ImageCroppedEvent, LoadedImage } from 'ngx-image-cropper';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import {
  ProductCroppedImage,
  ProductImage,
} from '../model/IProductImageFiles.model';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss'],
})
export class ProductFormComponent implements OnInit {
  productForm: FormGroup;
  imageChangedEvent: any = '';
  croppedImage: any = '';
  croppedImages: ProductCroppedImage[] = [];
  current_file: File = null;
  productImages: ProductImage[] = [];

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private store: Store<AppState>,
    public dialog: MatDialog
  ) {
    this.productForm = this.formBuilder.group({
      nameProduct: [
        '',
        [Validators.required, Validators.min(3), Validators.max(50)],
      ],
      description: ['', [Validators.required, Validators.min(20)]],
      isAvailable: [false],
      price: [0.0, [Validators.required, Validators.min(0)]],
      quantity: [0, [Validators.required, Validators.min(0)]],
      category_id: ['', Validators.required],
    });
  }

  get f() {
    return this.productForm.controls;
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.productForm.invalid) {
      return;
    }
    this.store.dispatch(LoginUser(this.productForm.value));
  }

  getErrorMessage() {
    if (this.f['nameProduct'].hasError('required')) {
      return 'You must enter a product name !';
    }
    return '';
  }

  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
    this.current_file = event.target.files[0];
  }

  openDialog() {
    const dialogRef = this.dialog.open(ImageCropperDialog, {
      width: '50%',
      data: {
        image: this.imageChangedEvent,
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      let file = this.base64ToFile(result.data, this.current_file);
      let initail_prod = this.searchFileInCroppedAndProductImages();
      if (initail_prod) {
        this.productImages.push({
          id: initail_prod.productImage.id,
          product_final_image: file,
        });
        this.croppedImages.push({
          id: initail_prod.productImage.id,
          product_cropped_image: result.data,
        });
      }
      this.productImages.push({
        id: this.productImages.length + 1,
        product_final_image: file,
      });
      this.croppedImages.push({
        id: this.productImages.length + 1,
        product_cropped_image: result.data,
      });
      this.imageChangedEvent = '';
      this.croppedImage = '';
      this.current_file = null;
    });
  }

  onImageEdit(id: number): void {
    const { productImage, croppedImage } =
      this.searchFileInCroppedAndProductImagesById(id);
    this.imageChangedEvent = croppedImage.product_cropped_image;
    this.current_file = productImage.product_final_image;
    this.openDialog();
  }

  onImageDelete(id: number): void {
    const { productImage, croppedImage } =
      this.searchFileInCroppedAndProductImagesById(id);
    this.productImages = this.productImages.filter(
      (element) => element != productImage
    );
    this.croppedImages = this.croppedImages.filter(
      (element) => element != croppedImage
    );
  }

  base64ToFile(croppedImage: string, originalFile: File): File {
    return new File([croppedImage], originalFile.name, {
      type: originalFile.type,
    });
  }

  searchFileInCroppedAndProductImagesById(id: number) {
    const productImage: ProductImage = this.productImages.filter(
      (x) => (x.id = id)
    )[0];
    const croppedImage: ProductCroppedImage = this.croppedImages.filter(
      (x) => (x.id = id)
    )[0];
    return { productImage: productImage, croppedImage: croppedImage };
  }

  searchFileInCroppedAndProductImages() {
    if (this.productImages.length > 0) {
      const product_image: ProductImage = this.productImages.filter((x) => {
        return x.product_final_image.name === this.current_file.name;
      })[0];
      const { productImage, croppedImage } =
        this.searchFileInCroppedAndProductImagesById(product_image.id);
      return { productImage: productImage, croppedImage: croppedImage };
    }
    return null;
  }
}

@Component({
  selector: 'app-product-image-cropper-dialog',
  template: `<h1 mat-dialog-title>Product Image</h1>
    <div mat-dialog-content class="overflow-hidden">
      <p>Crop product image for better presentation</p>
      <div
        [ngClass]="{ 'd-none': !isLoading }"
        class="w-100 d-flex justify-content-center"
      >
        <mat-spinner></mat-spinner>
      </div>
      <div [ngClass]="{ 'd-none': isLoading }" class="container-fluid d-flex">
        <div class="col-6">
          <span>Cureent image</span>
          <image-cropper
            class="cropper"
            (cropperReady)="cropperReady()"
            (imageCropped)="imageCropped($event)"
            (imageLoaded)="imageLoaded($event)"
            (loadImageFailed)="loadImageFailed()"
            [aspectRatio]="5 / 3"
            [imageChangedEvent]="imageChangedEvent"
            [imageBase64]="condition && imageChangedEvent"
            [maintainAspectRatio]="true"
          ></image-cropper>
        </div>
        <div class="col-6">
          <span>Image Preview</span>
          <img width="100%" height="100%" [src]="croppedImage" />
        </div>
      </div>
    </div>
    <mat-divider class="mt-2"></mat-divider>
    <div mat-dialog-actions class="d-flex justify-content-end w-100">
      <button (click)="onSave()" mat-button>Save image</button>
    </div>`,
  styles: [],
})
export class ImageCropperDialog implements OnInit {
  imageChangedEvent: any = '';
  croppedImage: any = '';
  isLoading: boolean = true;
  condition: boolean = false;

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    public dialogRef: MatDialogRef<ImageCropperDialog>
  ) {}

  ngOnInit(): void {
    this.imageChangedEvent = this.data.image;
    if (typeof this.imageChangedEvent === 'string') this.condition = true;
  }

  onSave() {
    this.dialogRef.close({ data: this.croppedImage });
  }

  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
  }

  imageLoaded(image: LoadedImage) {
    this.isLoading = false;
  }

  cropperReady() {}

  loadImageFailed() {}
}
