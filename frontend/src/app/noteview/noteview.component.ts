import { Component } from '@angular/core';
import { Note } from '../mainsv/data/note';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { RequestEngine } from '../mainsv/requestengine';
import { MainService } from '../mainsv/main.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import {Location} from '@angular/common';
import { None, Option, isNone, isSomeAnd, map } from "../option/option.module";
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
    selector: 'app-noteview',
    standalone: true,
    templateUrl: './noteview.component.html',
    styleUrls: ['./noteview.component.scss'],
    imports: [
        MatButtonModule,
        MatProgressSpinnerModule,
        CommonModule
    ]
})

export class NoteviewComponent {

    private id: Option<number>;
  
    protected note: Option<Note>;
  
    constructor(
        protected readonly mainService: MainService,
        protected readonly reqeng: RequestEngine,
        protected readonly route: ActivatedRoute,
        protected readonly loc: Location,
        protected readonly router: Router
    ) {}

    ngOnInit() {
        this.route.queryParamMap.subscribe(params => {
            this.processQueryParameterChanges(params);
        });
    }
  
    private processQueryParameterChanges(params: ParamMap) {
        let previousId = this.id;
        let id = parseInt(params.get('id')+"", 10);
        if(!Number.isInteger(id)) {
            return;
        }
        this.id = id;
        if(this.id == previousId) {
            return;
        }
  
        this.reloadViewState();
    }
  
    private reloadViewState() {
        if(isNone(this.id)) {
            this.note = None;
            return;
        }
        this.reqeng.noteGet(this.id).subscribe(note => {
            this.note = note;
        });
    }
  
    protected goBack() {
        this.router.navigate(['/notes']);
        // interface INavigationId {
        //     navigationId: number;
        // }

        // const navigationState = this.loc.getState() as Option<INavigationId>;

        // if(isSomeAnd(navigationState?.navigationId, id => id !== 1)) {
        //     this.loc.back();
        // } else {
        //     this.router.navigate(['/notes']);
        // }
    }

    protected editNote() {
        this.router.navigate(['/editnote'], { queryParams: {id: this.id} });
    }

    protected deleteNote() {
        this.reqeng.noteDelete(this.id!).subscribe(() => {
            this.router.navigate(['/notes']);
        })
    }

    protected simplifiedCreationDate(): Option<string> {
        return map(this.note?.createdAt, this.simplifyDate);
    }

    protected simplifiedEditionDate(): Option<string> {
        return map(this.note?.editedAt, this.simplifyDate);
    }

    private simplifyDate(dateIso8601: string): string {
        if (!dateIso8601) {
            return '';
        }
      
        const date = new Date(dateIso8601);
        const year = date.getFullYear();
        const month = ('0' + (date.getMonth() + 1)).slice(-2);
        const day = ('0' + date.getDate()).slice(-2);
        const hours = ('0' + date.getHours()).slice(-2);
        const minutes = ('0' + date.getMinutes()).slice(-2);

        return `${year}-${month}-${day} ${hours}:${minutes}`;
    }

}
