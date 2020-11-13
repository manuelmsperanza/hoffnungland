import { Component } from '@angular/core';
import { DomSanitizer, SafeUrl, SafeScript, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title : string = 'cv-app';
  
  trustedFacebookUrl : SafeUrl;
  trustedTwitterUrl : SafeUrl;
  trustedInstagramUrl : SafeUrl;
  trustedLinkedinUrl : SafeUrl;
  trustedGithubUrl : SafeUrl;
  trustedStackOverflowUrl : SafeUrl;
  trustedLinkedinBadge : SafeUrl;
  trustedW3CssUrl : SafeUrl;
  trustedPositiveSSLBadge : SafeHtml;

  constructor(private sanitizer: DomSanitizer) {
    this.trustedFacebookUrl = sanitizer.bypassSecurityTrustUrl("https://www.facebook.com/manuel.m.speranza");
    this.trustedTwitterUrl = sanitizer.bypassSecurityTrustUrl("https://twitter.com/SperanzaManuel");
    this.trustedInstagramUrl = sanitizer.bypassSecurityTrustUrl("https://www.instagram.com/manuel.m.speranza/");
    this.trustedLinkedinUrl = sanitizer.bypassSecurityTrustUrl("https://www.linkedin.com/in/manuelmsperanza");
    this.trustedGithubUrl = sanitizer.bypassSecurityTrustUrl("https://github.com/manuelmsperanza");
    this.trustedStackOverflowUrl = sanitizer.bypassSecurityTrustUrl("https://stackoverflow.com/users/8607724/pin%c3%b6ch");
    this.trustedLinkedinBadge = sanitizer.bypassSecurityTrustUrl("https://it.linkedin.com/in/manuelmsperanza?trk=profile-badge");
    this.trustedW3CssUrl = sanitizer.bypassSecurityTrustUrl("https://www.w3schools.com/w3css/tryit.asp?filename=tryw3css_templates_cv&amp;stacked=h");
  }
}
