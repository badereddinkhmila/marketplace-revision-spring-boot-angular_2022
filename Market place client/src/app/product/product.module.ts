import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductListComponent} from './product-list/product-list.component';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from "@angular/material/grid-list";
import {ProductItemComponent} from './product-item/product-item.component';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {SwiperModule} from "swiper/angular";
import {ImageCropperDialog, ProductFormComponent} from './product-form/product-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {ImageCropperModule} from "ngx-image-cropper";
import {MatInputModule} from "@angular/material/input";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatDialogModule} from "@angular/material/dialog";
import {MatDividerModule} from "@angular/material/divider";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import { ProductImagePreviewComponent } from '../components/product-image-preview/product-image-preview.component';
import { ComponentsModule } from '../components/components.module';


@NgModule({
  declarations: [
    ProductListComponent,
    ProductItemComponent,
    ProductFormComponent,
    ImageCropperDialog
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatGridListModule,
    MatIconModule,
    MatButtonModule,
    SwiperModule,
    ImageCropperModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule,
    MatDialogModule,
    MatDividerModule,
    MatProgressSpinnerModule,
    ComponentsModule
  ],
  exports: [
    ProductListComponent,
    ProductFormComponent
  ]
})
export class ProductModule {
}
