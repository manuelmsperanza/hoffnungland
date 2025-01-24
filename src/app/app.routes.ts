import { Routes } from '@angular/router';
import { CallbackAuth0Component } from './callback-auth0/callback-auth0.component';

export const routes: Routes = [
    //{ path: '', component: HomeComponent },
    { path: 'callback', component: CallbackAuth0Component },
    { path: '**', redirectTo: '' }];
