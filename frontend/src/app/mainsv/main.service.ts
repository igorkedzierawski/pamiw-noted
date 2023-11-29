import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { LoggedUser } from './data/loggeduser';
import { RequestEngine } from './requestengine';
import { Router } from '@angular/router';
import { isSome, isSomeAndNotNull } from '../option/option.module';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
    providedIn: 'root'
})
export class MainService {

    private readonly userSubject: Subject<LoggedUser | null> = new Subject<LoggedUser | null>();
    public readonly user$: Observable<LoggedUser | null> = this.userSubject.asObservable();
    
    private readonly isLoggedInSubject: Subject<boolean> = new Subject<boolean>();
    public readonly isLoggedIn$: Observable<boolean> = this.isLoggedInSubject.asObservable();

    constructor(
        private readonly keycloak: KeycloakService,
        private readonly reqeng: RequestEngine,
        private readonly router: Router
    ) {
        this.reqeng.userinfo().subscribe(user => {
            if(isSomeAndNotNull(user)) {
                if(user!.avatar_url === null || user!.avatar_url === undefined) {
                    user!.avatar_url = "https://i.imgur.com/DaMAw1y.png";
                }
            }
            this.userSubject.next(user);
        });

        this.user$.subscribe(user => {
            if(isSome(user)) {
                this.isLoggedInSubject.next(isSomeAndNotNull(user));
            }
        })
    }

    public redirectToLoginPage() {
        this.keycloak.login();
    }

    public redirectToNotesPage() {
        this.router.navigate(['/notes']);
    }

    public redirectToCreateNotePage() {
        this.router.navigate(['/createnote']);
    }

    public redirectToHomepagePage() {
        window.location.href = "/";
    }

    public performLogoutProcedure() {
        this.keycloak.logout();
    }

}
