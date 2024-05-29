import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';
import { AdminComponent } from './admin/admin.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditProductComponent } from './edit-product/edit-product.component';
import { EditServiceProdComponent } from './edit-service-prod/edit-service-prod.component';
import { AddProductComponent } from './add-product/add-product.component';
import { AddServiceProdComponent } from './add-service-prod/add-service-prod.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    AdminPanelComponent,
    EditProductComponent,
    EditServiceProdComponent,
    AddProductComponent,
    AddServiceProdComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    provideHttpClient(withFetch()),
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
