import { Injectable, Signal, WritableSignal, signal } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable, map, timeout, take, catchError, throwError, tap, of } from 'rxjs';
import { LoggedUser } from './data/loggeduser';
import { Note } from './data/note';
import { Page } from './data/page';
import { NoteForm } from './data/noteform';
import { NoteDeletionResponse } from './data/notedeletionresponse';
import { RegisterForm } from './data/registerform';

@Injectable({
    providedIn: 'root'
})
export class RequestEngine {
    
    private readonly USERINFO = '/api/v1/auth/userinfo';
    private readonly REGISTER = '/api/v1/auth/register';

    private readonly NOTE_CREATE = '/api/v1/note/create';
    private readonly NOTE_UPDATE = '/api/v1/note/update';
    private readonly NOTE_DELETE = '/api/v1/note/delete';
    private readonly NOTE_PAGE = '/api/v1/note/page';
    private readonly NOTE_GET = '/api/v1/note/get';

    //todo:
    //  - gdy endpoint robi redirect na '/login' to łapać to i redirectować użytkownika na '/login'
    constructor(private readonly http: HttpClient) {}

    public userinfo(): Observable<LoggedUser | null> {
        return this.http.get<LoggedUser>(this.getOrigin() + this.USERINFO, {})
            .pipe(catchError(() => {return of(null);}))
            .pipe(take(1));
    }

    public notePage(page: number, size: number): Observable<Page<Note>> {
        return this.http.get<Page<Note>>(this.getOrigin()+this.NOTE_PAGE, {params: {
            page: page,
            size: size
        }}).pipe(take(1));
    }

    public noteGet(id: number): Observable<Note> {
        return this.http.get<Note>(this.getOrigin()+this.NOTE_GET, {params: {
            id: id
        }}).pipe(take(1));
    }

    public noteCreate(form: NoteForm): Observable<Note> {
        return this.http.post<Note>(this.getOrigin()+this.NOTE_CREATE, form)
            .pipe(take(1));
    }

    public noteUpdate(id: number, form: NoteForm): Observable<Note> {
        return this.http.patch<Note>(this.getOrigin()+this.NOTE_UPDATE, form, {params: {
            id: id
        }}).pipe(take(1));
    }

    public noteDelete(id: number): Observable<NoteDeletionResponse> {
        return this.http.delete<NoteDeletionResponse>(this.getOrigin()+this.NOTE_DELETE, {params: {
            id: id
        }}).pipe(take(1));
    }

    public registerUser(form: RegisterForm): Observable<LoggedUser> {
        return this.http.post<LoggedUser>(this.getOrigin()+this.REGISTER, form)
            .pipe(take(1));
    }
    
    private getOrigin(): string {
        return window.location.origin;
    }

}
