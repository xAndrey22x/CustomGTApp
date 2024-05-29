import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AdminComponent } from './admin/admin.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AuthGuard } from './authentification/auth.guard';
import { EditProductComponent } from './edit-product/edit-product.component';
import { EditServiceProdComponent } from './edit-service-prod/edit-service-prod.component';
import { AddProductComponent } from './add-product/add-product.component';
import { AddServiceProdComponent } from './add-service-prod/add-service-prod.component';

const routes: Routes = [
  { path: '', redirectTo: 'admin', pathMatch: 'full'},
  { path: 'admin', component: AdminComponent },
  { path: 'admin-panel', component: AdminPanelComponent, canActivate: [AuthGuard]},
  { path: 'admin-panel', component: AdminPanelComponent, canActivate: [AuthGuard] },
  { path: 'edit-product/:id', component: EditProductComponent, canActivate: [AuthGuard] },
  { path: 'add-product', component: AddProductComponent, canActivate: [AuthGuard] },
  { path: 'edit-service-prod/:id', component: EditServiceProdComponent, canActivate: [AuthGuard] },
  { path: 'add-service-prod', component: AddServiceProdComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
