import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotelistComponent } from './notelist/notelist.component';
import { NoteviewComponent } from './noteview/noteview.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { RegisterpageComponent } from './registerpage/registerpage.component';
import { NotecreateComponent } from './noteeditor/notecreate/notecreate.component';
import { GuestsToLoginPageRedirectGuard, StrayPathsToNotesRedirectGuard, UsersAwayFromAuthenticationRedirectGuard } from './redirect.guard';
import { NoteeditComponent } from './noteeditor/noteedit/noteedit.component';
import { NopComponent } from './nop/nop.component';
import { HomepageComponent } from './homepage/homepage.component';

const routes: Routes = [
    { path: 'notes', component: NotelistComponent, canActivate: [] },
    { path: 'note', component: NoteviewComponent, canActivate: [] },
    { path: 'editnote', component: NoteeditComponent, canActivate: [] },
    { path: 'createnote', component: NotecreateComponent, canActivate: [] },
    { path: 'login', component: LoginpageComponent, canActivate: [] },
    { path: 'register', component: RegisterpageComponent, canActivate: [] },
    { path: '', component: HomepageComponent },
    { path: '**', component: NopComponent, canActivate: [StrayPathsToNotesRedirectGuard] },
];
// const routes: Routes = [
//     { path: 'notes', component: NotelistComponent, canActivate: [GuestsToLoginPageRedirectGuard] },
//     { path: 'note', component: NoteviewComponent, canActivate: [GuestsToLoginPageRedirectGuard] },
//     { path: 'editnote', component: NoteeditComponent, canActivate: [GuestsToLoginPageRedirectGuard] },
//     { path: 'createnote', component: NotecreateComponent, canActivate: [GuestsToLoginPageRedirectGuard] },
//     { path: 'login', component: LoginpageComponent, canActivate: [UsersAwayFromAuthenticationRedirectGuard] },
//     { path: 'register', component: RegisterpageComponent, canActivate: [UsersAwayFromAuthenticationRedirectGuard] },
//     { path: '', component: HomepageComponent },
//     { path: '**', component: NopComponent, canActivate: [StrayPathsToNotesRedirectGuard] },
// ];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
