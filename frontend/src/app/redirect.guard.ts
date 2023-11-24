import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { MainService } from './mainsv/main.service';
import { Observable, map, of, take, tap } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class GuestsToLoginPageRedirectGuard implements CanActivate {
    
    constructor(private readonly mainService: MainService) {}
  
    canActivate(): Observable<boolean> {
        return of(true);
        // return this.mainService.isLoggedIn$.pipe(map(b => b)).pipe(map(b => true));
        // return this.mainService.isLoggedIn$.pipe(map(isLoggedIn => {
        //     console.log("SECUREPAGE PATH DETECTED; isLoggedIn="+isLoggedIn)
        //     // if(!isLoggedIn) {
        //     //     console.log("REDIRECT TO: redirectToLoginPage");
        //     //     window.location.href = "/login";
        //     //     // this.mainService.redirectToLoginPage();
        //     //     return true;
        //     // } else {
        //     //     console.log("REDIRECT TO: aborted");
        //     //     return true;
        //     // }
        //     return isLoggedIn;
        // }));
    }

}

@Injectable({
    providedIn: 'root',
})
export class StrayPathsToNotesRedirectGuard implements CanActivate {
    
    constructor(private readonly mainService: MainService) {}
  
    canActivate(): Observable<boolean> {
        return this.mainService.isLoggedIn$.pipe(map(isLoggedIn => {
            console.log("STRAY PATH DETEDTED; isLoggedIn="+isLoggedIn)
            if(isLoggedIn) {
                console.log("REDIRECT TO: redirectToNotesPage");
                window.location.href = "/notes";
                // this.mainService.redirectToNotesPage();
            } else {
                console.log("REDIRECT TO: redirectToHomepagePage");
                window.location.href = "/";
                // this.mainService.redirectToHomepagePage();
            }
            return false;
        }));
    }

}

@Injectable({
    providedIn: 'root',
})
export class UsersAwayFromAuthenticationRedirectGuard implements CanActivate {
    
    constructor(private readonly mainService: MainService) {}
  
    canActivate(): Observable<boolean> {
        return of(true);
        // return this.mainService.isLoggedIn$.pipe(tap(isLoggedIn => {
        //     console.log("LOGIN PATH DETECTED; isLoggedIn="+isLoggedIn)
        //     if(isLoggedIn) {
        //         console.log("REDIRECT TO: redirectToNotesPage");
        //         this.mainService.redirectToNotesPage();
        //     }
        // }));
    }

}