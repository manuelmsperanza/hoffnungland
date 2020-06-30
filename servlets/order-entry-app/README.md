# OrderEntryApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.1.7.

## Creation

	ng new order-entry-app --routing

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

### Build for orderEntryWeb
	
	ng build --prod --base-href=/orderEntryWeb/

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Add Service Worker

	ng add @angular/pwa --project order-entry-app

## Add dependencies

### Bootstrap
[ng-bootstrap.github.io](https://ng-bootstrap.github.io/)

	ng add @ng-bootstrap/ng-bootstrap

### Material
[material.angular.io](https://material.angular.io/)

	ng add @angular/material

## Add Modules

### Catalog Module
	
	ng g m catalog -m app --routing

### Orders Module
	
	ng g m orders -m app --routing
	
#### New Order Component
	
	ng g c orders/new-order

#### List Orders Component
	
	ng g c orders/list-orders