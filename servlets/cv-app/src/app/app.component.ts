import { Component } from '@angular/core';
import { DomSanitizer, SafeUrl, SafeScript, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title : string = 'cv-app';
  
  trustedLinkedinBadge : SafeHtml;

  constructor(private sanitizer: DomSanitizer) {
    //this.trustedLinBadgeUrl = sanitizer.bypassSecurityTrustUrl("https://it.linkedin.com/in/manuelmsperanza?trk=profile-badge");
    this.trustedLinkedinBadge = sanitizer.bypassSecurityTrustHtml('<script type="text/javascript" src="https://platform.linkedin.com/badges/js/profile.js" async defer></script>' +
    '<div id="linkedInBadge" class="LI-profile-badge"  data-version="v1" data-size="large" data-locale="en_US" data-type="horizontal" data-theme="light" data-vanity="manuelmsperanza">' + 
      '<a class="LI-simple-link" href="https://it.linkedin.com/in/manuelmsperanza?trk=profile-badge">Manuel Speranza</a>' +
   '</div>')
  }

}
